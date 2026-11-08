# Into the Breach — Guía de diseño incremental para el TP2 de Paradigmas

**Estado:** borrador de trabajo, para discutir en equipo.  
**Fuentes revisadas:** `Casos de uso.txt`, `Elementos del dominio.txt`, `Inicializaciones.txt`, `Posibles Implementaciones.txt`, `Preguntas.txt`, `Casilla.java`, `Posicion.java`, diagramas de Martin y Lau y enunciado del TP2 2026 2C incluidos en el ZIP.

## 1. Acuerdo metodológico

El grupo seguirá **TDD (rojo → verde → refactor)**. No se crearán interfaces para `RecepcionDeDanio`, `RecepcionDeUnidades`, `Efecto`, `Alcance`, `EstadoSalud` o `Fase` solo por anticipar posibles variaciones. Un `if` sencillo, **dentro de la clase responsable del estado o comportamiento**, es aceptable en la primera implementación. Si las pruebas muestran que las variaciones crecen o se repiten, se evaluará extraer comportamiento polimórfico.

Para decidir si refactorizar, buscar evidencia: condicionales repetidos en varios lugares, necesidad de agregar un tipo cambiando muchas clases, responsabilidades mezcladas, tests demasiado acoplados a detalles, o reglas que ya no se pueden explicar con claridad. **La meta no es eliminar todos los condicionales: es mantener responsabilidades claras.**

## 2. Propuesta mínima de diseño (no es una decisión definitiva)

| Clase / concepto | Responsabilidad inicial | Decisión por validar |
|---|---|---|
| `Partida` | Orquestar los turnos y verificar fin de partida. | Si habrá una clase `Turno` o bastará contar turnos. |
| `Tablero` | Mantener las 64 casillas, consultar posiciones, validar caminos y coordinar traslados y empujes. | Matriz 8×8 vs. `Map<Posicion, Casilla>`; matriz simplifica. |
| `Casilla` | Conocer su terreno y su ocupante (si lo hay); recibir efectos de ataques y traslados. | No tiene por qué almacenar posición si el tablero la conoce. |
| `Posicion` | Valor **inmutable** con fila y columna; permitir comparar posiciones y calcular vecinos. | No debería cambiar las coordenadas de otra posición. |
| `Terreno` | Distinguir suelo, edificio, montaña, agua, ácido; aplicar efectos/reglas. | Empezar con lo más simple que pase tests; clases distintas cuando sus comportamientos lo exijan. |
| `Unidad` | Vida, estado, capacidades, recibir daño y decidir condiciones propias de acciones. | No es obligatorio que almacene posición para poseer reglas de movimiento. |
| `Mech` / `Vek` | Reglas particulares (acciones del jugador, ataques anunciados, IA, etc.). | Herencia donde aporte comportamiento común real. |
| `Arma` | Determinar objetivos y efectos de ataques; distinguir puño, cañón, artillería. | Clases, variantes o condicionales según tests. |
| `RedElectrica` | Mantener y descontar energía. | Una única instancia compartida por edificios, no una por edificio. |

### Decisión de ubicación/ocupación: adoptar solo **una fuente de verdad**

**Ruta A — recomendación inicial:** `Tablero` ubica casillas, `Casilla` contiene `Unidad`, `Unidad` no conserva `Posicion` propia. Un movimiento se inicia con origen y destino (`tablero.mover(origen, destino)`), el tablero valida trayectoria y la casilla origen transfiere la unidad al destino. **Las condiciones propias del Mech/Vek pueden seguir siendo responsabilidades de la unidad.**

**Ruta B — alternativa válida:** `Unidad` guarda `Posicion`, y `Tablero` guarda una colección de unidades/índice de ocupación. Puede ser cómodo para desplazamientos y ataques relativos, pero hay que evitar duplicar la posición en `Casilla` o mantener múltiples índices inconsistentes.

**Evitar por ahora:** que `Unidad` almacene una posición *y* `Casilla` un ocupante mutables por separado, sin una sola operación que garantice la consistencia.

### Movimiento voluntario, ataque y empuje son operaciones diferentes

1. **Movimiento voluntario:** comprobar turno y acciones disponibles, camino dentro de alcance y transitabilidad, posición final libre, efectos de terreno y actualización atómica de la ocupación. Los Mechs tienen movimiento 3; el Avispón puede *sobrevolar* obstáculos y unidades, pero debe terminar libre. Los Vek terrestres no eligen como destino final agua ni ácido.
2. **Ataque:** comprobar que la unidad puede atacar, aplicar patrón y alcance del arma. La artillería daña **la casilla objetivo** y empuja **a las unidades de las cuatro casillas vecinas**, no a la unidad de la casilla objetivo por defecto.
3. **Empuje:** calcula destino a una casilla en la dirección del golpe. Si el destino es transitable y libre, traslada y aplica efectos del terreno; si está ocupado o es obstáculo, no traslada y aplica colisión; fuera del tablero, no traslada. **No se validan el alcance ni la acción voluntaria del empujado.**

## 3. Contradicciones y riesgos concretos detectados

| Fuente / idea | Problema o tensión | Resolución sugerida |
|---|---|---|
| `Elementos del dominio.txt` y `Posibles Implementaciones.txt` | En unos lugares `Unidad` tiene `Posicion` y en otros `Casilla` tiene `Unidad`. | Elegir una fuente de verdad para ocupación, documentar y probar conservación antes de programar ataques. |
| `Posicion.java` | `desplazarA()` cambia internamente las coordenadas de otra instancia. Si la misma posición se comparte, puede cambiar su significado en todo el modelo. | Crear posiciones inmutables y asignar nuevas referencias o trasladar unidades entre casillas. |
| `Casilla.java` | `recibirAtaque` llama incondicionalmente a `unidad`: falla si está vacía; `Ataque` está escrito con mayúscula distinta al parámetro, y `ocupar()` no está implementado. | `if (unidad != null) unidad.recibirDanio(...)`, delegar el daño al terreno si corresponde, y compilar con un primer test. |
| `Casilla.moverUnidad` | El destino puede rechazar al ocupante y no hay validación visible; si se mueve primero y falla después, puede dejar estado parcial. | Validar todo antes de mutar (o hacer una transferencia que solo vacíe origen tras recepción exitosa). |
| `Casos de uso.txt`: artillería y Vek | Se plantea que la artillería le pega directamente al Vek y a la vez lo empuja contra el edificio. En el enunciado la artillería empuja *adyacentes*, no su objetivo central. | Para el escenario de la Entrega 0, poner el **Vek en una casilla adyacente al objetivo**, con edificio en la siguiente casilla hacia afuera. El objetivo central puede estar vacío u ocupado (y si está ocupado recibe daño). |
| `Elementos del dominio.txt`: «montañas no reciben daño» | Correcto, pero la montaña **sí** bloquea movimiento y disparos; el arma cañón se detiene en el primer obstáculo. | Separar alcance del disparo, daño al impacto y empuje. |
| `Diagrama_secuencia_0.puml` | `edificio` crea `Suelo` y luego avisa a `Casilla` para cambiar terreno; puede funcionar, pero una referencia al edificio destruido se vuelve un objeto obsoleto. | Concentrar transición de terreno en un único lugar y comprobar el nuevo tipo después de destruir. |
| `DiagramaDeClaseTentativo.puml` | Modela simultáneamente interfaces `Fase`, `EstadoSalud`, `Alcance`, `Efecto`, `Impactable`, más múltiples clases específicas antes de tests. | Conservarlo como **mapa de posibles refactors**, no como lista obligatoria para el primer commit. |
| `Inicializaciones.txt` | Inyectar la misma `RedElectrica` en cada edificio es razonable; cuidado con crear accidentalmente una nueva por edificio. | Test que pruebe que el daño a distintos edificios modifica la misma energía. |
| Enunciado: deshacer | El movimiento se revierte **incluidos sus efectos**. Si la unidad ya estaba contaminada antes, ponerla simplemente en «Sana» sería incorrecto. | Guardar un snapshot mínimo del estado previo al movimiento y restaurarlo, con límite de una vez por turno y prohibición tras atacar. |
| Enunciado: IA y ataques anunciados | Si el Vek es empujado, el ataque anunciado se traslada *relativamente* con él; si se guarda una coordenada absoluta sin actualizaciones, falla. | Representar anuncio con **dirección relativa** y obtener objetivo desde la posición actual cuando se resuelva; test específico. |

## 4. Prioridades TDD: primeras pruebas (Entrega 1)

**Vertical 1 — tablero y ocupación:**
- `tableroCreadoTiene64CasillasDeMapaPredefinido`.
- `casillaLibreRecibeUnaUnidad` / `casillaOcupadaNoRecibeSegundaUnidad`.
- `moverActualizaOrigenYDestinoSinDuplicarUnidad`.
- `moverFueraDeTableroNoCambiaEstado`.

**Vertical 2 — movimiento real:**
- `mechNoSuperaRangoDeTres`.
- `mechNoAtraviesaUnidadNiMontana`.
- `mechPuedeRodearObstaculoSiHayCaminoEnRango` (si corresponde: evaluar caminos ortogonales y no solo distancia Manhattan).
- `avispónPuedeSobrevolarPeroNoTerminarEnCasillaOcupada` (más adelante).

**Vertical 3 — combate:**
- `punoDaniaDosYEmpujaEnDireccionDelGolpe`.
- `canonImpactaSoloPrimerObjetivoEnLinea`.
- `artilleriaIgnoraObstaculosIntermediosYNoAtacaAdyacente`.
- `artilleriaDaniaCentroYEmpujaVecinosOrtogonalmente`.

**Vertical 4 — acciones y deshacer:**
- `mechNoSeMueveDosVecesEnMismoTurno`.
- `mechNoAtacaDosVecesEnMismoTurno`.
- `deshacerRestituyeUbicacionYEstadoPrevio`.
- `deshacerNoEstaDisponibleTrasAtacarNiDosVecesEnTurno`.

No hace falta implementar toda la jerarquía ni la GUI para estos tests. No crear interfaces si basta con un pequeño método y un condicional que permanece en el objeto correcto.

## 5. Pruebas de reglas que no deben olvidarse (Entrega 2)

- Al chocar contra unidad o edificio, el empujado **no se mueve** y recibe 1 de daño; el objeto con el que choca también recibe 1 (montaña no).
- Empujar fuera del tablero **no** aplica daño por sí mismo.
- Un Vek terrestre empujado al agua muere; un Mech en el agua no puede atacar; el Avispón no sufre efecto del agua ni del ácido.
- Contaminación duplica ataques y choques, sin acumular; los edificios no se contaminan.
- Vek terrestres **no eligen voluntariamente** terminar en agua o ácido.
- Un ataque anunciado por Vek se ejecuta en orden; si el Vek muere antes, no ataca. Si es empujado, el anuncio cambia de objetivo por conservar dirección relativa.
- Una unidad en casilla de emergencia impide aparición y recibe 1 de daño.
- Red Eléctrica empieza en 5; cada edificio dañado resta 1.

## 6. Decisiones abiertas que sí requieren discusión

1. **Fuente de verdad de ocupación:** Ruta A (`Casilla` contiene `Unidad`) o Ruta B (`Unidad` tiene `Posicion`). No implementar ambas a la vez.
2. **Rutas de movimiento:** ¿quién busca caminos ortogonales válidos? Recomendación: `Tablero` ofrece la operación; `Unidad` define limitaciones propias.
3. **Transición de edificio destruido a suelo:** ¿quién ejecuta la sustitución de `Terreno`? Elegir un único dueño del cambio.
4. **Estado para deshacer:** ¿cómo guardar exactamente el estado previo sin revertir efectos de ataques ajenos? La consigna menciona explícitamente los efectos del propio movimiento.
5. **Formato de anuncios:** dirección relativa al Vek, no solo coordenada absoluta de casilla objetivo.
6. **Supuestos por aclarar con el corrector:** empates de IA, comportamiento de colisión si una unidad contaminada muere, y otras lagunas reales que surjan de tests; no inventar reglas del videojuego comercial que contradigan el TP.

## 7. Criterios para el primer diseño y refactors posteriores

- `if (unidad != null)` **dentro de `Casilla.recibirDanio`**: razonable.
- Desde fuera, preguntar `casilla.estaOcupada()` y después `casilla.getUnidad().recibirDanio(...)`: evitar; manda el mensaje `casilla.recibirImpacto(...)`.
- `if (terrenoEsAgua)` repetido en `Unidad`, `Casilla`, `Tablero`, `Arma`: refactor probable para mover el comportamiento a `Terreno`.
- Implementaciones duplicadas de puño/cañón/artillería o condiciones de alcance extendidas: recién ahí extraer estrategias o polimorfismo.
- Clases por cada fase y variante de estado: introducirlas cuando sus transiciones tengan comportamiento suficiente para merecerlas.
- Después de cada ciclo rojo/verde/refactor, mantener CI, pruebas y el diagrama de secuencia actualizado con **mensajes efectivamente implementados**.

## 8. Primer corte de implementación recomendado

**Meta del primer corte:** crear un tablero 8×8 con terrenos, ubicar una unidad, moverla por un camino válido a una casilla libre y dejar origen vacío. **Nada de ataques ni IA todavía.**

Al pasar esos tests, agregar daños simples; después puño, cañón, artillería y empuje/colisión. El primer diagrama de secuencia de la Entrega 0 debe representar correctamente el caso de Artillería descrito arriba, pero puede simplificarse conforme evolucione el código.

> Principio de trabajo: **el modelo se afina gracias a las pruebas y al código, no intentando adivinar por adelantado todas las interfaces del juego.**
