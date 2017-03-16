package dao;


import java.util.List;

public interface TemplateDao<E, K> {
    void save(E entity);

    List<E> getAll();

    E getById(K id);

    void update(E entity);

    void delete(K id);
}