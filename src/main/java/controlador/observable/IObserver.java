package controlador.observable;

/**
 * Interfaz que deberan implementat todos aquellos objetos que deban responder a los
 * cambios de estado de los objetos observados
 */
public interface IObserver {
  /**
   * Metodo donde se realizara las operaciones asociadas al cambio de estado
   */
  void update();
}
