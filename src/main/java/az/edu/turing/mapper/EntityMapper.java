package az.edu.turing.mapper;

public interface EntityMapper <D, E>{

    D toDto(E e);
}
