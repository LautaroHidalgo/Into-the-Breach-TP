@Test
void mechNoPuedeMoverseDosVecesPorTurno() {

    // ARRANGE
    Tablero tablero = new Tablero(); " tablero vacío, para estos tests DUDA"
    Posicion origen   = new Posicion(0, 0);
    Posicion destino1 = new Posicion(0, 1);
    Posicion destino2 = new Posicion(0, 2);
    MechCombate mech  = new MechCombate();
    tablero.inicializarUnidad(mech, origen);

    // ACT
    tablero.moverUnidad(origen, destino1);

    // ASSERT
    assertThrows(
        MovimientoNoPermitidoException.class,
        () -> tablero.moverUnidad(destino1, destino2)
    );
}

@Test
void mechNoPuedeAtacarDosVecesPorTurno() {
    // ARRANGE
    Tablero tablero = new Tablero();
    Posicion posicionMech = new Posicion(0, 0);
    Posicion posicionVek  = new Posicion(0, 1);
    MechCombate mech = new MechCombate();
    Saltador vek = new Saltador();
    tablero.inicializarUnidad(mech, posicionMech);
    tablero.inicializarUnidad(vek, posicionVek);

    // ACT
    mech.atacar(posicionMech, posicionVek, tablero);

    // ASSERT
    assertThrows(
            AccionNoPermitidaException.class,
            () -> mech.atacar(posicionMech, posicionVek, tablero);
    )
}

@Test
void sePuedeDeshacerMovimientoSiAunNoAtaco() {
    // ARRANGE
    Tablero tablero = new Tablero();
    Posicion origen = new Posicion(0, 0);
    Posicion destino = new Posicion(0, 1);
    MechCombate mech = new MechCombate();

    tablero.inicializarUnidad(mech, origen);
    tablero.moverUnidad(origen, destino);

    // ACT
    tablero.deshacerMovimiento(destino, origen);

    // ASSERT
    assertTrue(tablero.obtenerCasilla(destino).estaLibre());
    assertFalse(tablero.obtenerCasilla(origen).estaLibre());
}