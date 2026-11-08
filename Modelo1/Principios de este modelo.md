# Principios del Modelo1

## Distribución de responsabilidades

En este modelo, las responsabilidades quedan distribuidas de la siguiente manera:

- **Tablero:** coordina los movimientos voluntarios y forzados de las unidades, gestionando las interacciones entre las casillas y las restricciones espaciales del tablero.
- **Tablero:** administra las asociaciones `Map<Posicion, Casilla>`, utilizando objetos `Posicion` como claves para localizar las casillas sin necesidad de conocer la representación interna de sus coordenadas. El alcance de movimiento se establece inicialmente como una constante compartida de valor 3.
- **Posicion:** es un objeto de valor inmutable que encapsula sus coordenadas y ofrece comportamientos como obtener una posición adyacente en determinada dirección, calcular distancias y comparar posiciones mediante igualdad por valor.
- **Casilla:** administra su ocupante y el terreno que contiene, sin conocer su propia posición. Recibe ataques, coordina la recepción de unidades y delega en su terreno la resolución de los efectos correspondientes.
- **Terreno:** encapsula las restricciones de ingreso, las consecuencias de los desplazamientos forzados y los efectos particulares de cada tipo de terreno. Se utiliza composición para permitir que una casilla cambie de terreno sin modificar su identidad.
- **Unidad:** encapsula sus características, estado de salud y capacidades de desplazamiento. No conoce su posición ni administra directamente los traslados entre casillas.
- **Mech:** especializa a `Unidad`, administra su arma y registra las acciones realizadas durante su turno mediante `movimientoRealizado` y `ataqueRealizado`. Inicia los ataques y delega su ejecución en el arma correspondiente.
- **Vek:** especializa a `Unidad` y encapsula los comportamientos propios de los enemigos, como anunciar y ejecutar ataques durante las fases correspondientes.
- **Arma:** encapsula las características y el comportamiento de los ataques. Interactúa con el tablero cuando sus efectos requieren localizar casillas o producir desplazamientos forzados, sin delegarle la responsabilidad del ataque.
- **Edificio:** especializa a `Terreno`, administra su propia vida y las consecuencias de recibir ataques o colisiones. Cuando es destruido, provoca la transformación del terreno de su casilla en suelo y afecta a la red eléctrica.
- **RedElectrica:** administra los puntos de energía compartidos por los edificios y permite determinar cuándo se han agotado.
- **Partida:** coordina el desarrollo del juego, su inicialización, turnos, fases y condiciones de finalización.

## Criterios de diseño

- **Ocultamiento de información:** se evita exponer la representación interna de los objetos mediante getters. Se favorecen mensajes que expresen responsabilidades y comportamientos.
- **Tell, Don't Ask:** los objetos deben delegar acciones en sus colaboradores en lugar de consultar su estado para tomar decisiones que les corresponden.
- **Distribución de responsabilidades:** se busca mantener alta cohesión y bajo acoplamiento, evitando concentrar toda la lógica del juego en `Tablero` o `Partida`.
- **Ausencia de información espacial redundante:** ni `Unidad` ni `Casilla` almacenan posiciones. El tablero administra las asociaciones espaciales.
- **Composición sobre herencia cuando corresponde:** las casillas contienen terrenos que pueden reemplazarse dinámicamente. La herencia se reserva para especializaciones de comportamiento.
- **Polimorfismo:** se favorece la delegación a tipos especializados para resolver comportamientos que dependen del terreno, la unidad o el arma.
- **TDD y diseño incremental:** se prioriza implementar las funcionalidades necesarias para cada caso de uso, evitando abstracciones prematuras. Las interfaces y generalizaciones adicionales quedan sujetas a necesidades concretas de refactorización.

## Contras y riesgos del modelo

De momento, hemos logrado identificar los siguientes contras de nuestro primer modelo de implementación:

1. **Centralización de responsabilidades espaciales:** `Tablero` concentra la coordinación de movimientos, empujones y consultas espaciales. Existe el riesgo de que acumule demasiadas responsabilidades conforme aumente la complejidad del juego.
1. **Dependencia del tablero para conocer ubicaciones:** como las unidades y casillas desconocen sus posiciones, cualquier comportamiento que requiera información espacial necesita colaborar con `Tablero` o recibir

