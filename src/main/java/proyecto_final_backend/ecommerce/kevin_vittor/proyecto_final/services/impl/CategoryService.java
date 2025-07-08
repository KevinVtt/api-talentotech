package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.NotFoundException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.ProblemForPersists;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Category;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.ICategory;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.ICategoryService;

@Log4j2
@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategory categoryRepository;

    @Override
    @Transactional
    public void delete(Category object) {
        try{
            categoryRepository.delete(object);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No existe esa categoria para elimiar"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try{
            categoryRepository.deleteById(id);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No existe el id de esa categoria para elimiar"); // add customs exceptions
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        try{
            return categoryRepository.findAll();
        }catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Integer id) {
        try{
            return categoryRepository.findById(id);
        } catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional
    public Category save(Category object) {
        try{
            return categoryRepository.save(object);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede actualizar / guardar esa categoria"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public Category saveObject(Category category) {
        log.info("ID: " + category.getId());
        if(category.getId() == null){
            return save(category);
        }
        throw new ProblemForPersists("No se puede guardar esa categoria, el id ya existe"); // add customs exceptions
        // add custom exceptions!!!!
    }

    @Override
    @Transactional
    public Category updateObject(Integer id,Category category) {
        if(id != null){
            Category categoryDb = findById(id).orElseThrow( () -> new NotFoundException("No existe la categoria con esa id"));
            categoryDb.setName(category.getName());
            return save(categoryDb);
        }
        throw new ProblemForPersists("No se puede actualizar esa categoria, el id no existe"); // add customs exceptions
    }

}
