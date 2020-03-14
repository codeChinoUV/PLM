package controlador.observable;

/**
 * Interfaz que debera de implementar todos los objetos que queramos controlar su estado
 * @author Jose Miguel Quiroz
 */
public interface IObservable {
  /**
   * Metodo para añadir un objeto observador al objeto observable.
   * @param observer objeto que implementa la interfaz IObserver.
   */
  void agregarObserver(IObserver observer);

  /**
   * Metodo para eliminar un objeto observador al objeto observabel.
   * @param observer objeto que implementa la interfaz IObserver.
   */
  void eliminarObserver(IObserver observer);
}
