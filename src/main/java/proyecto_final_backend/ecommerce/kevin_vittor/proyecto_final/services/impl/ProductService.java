package proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.impl;

import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.NotFoundException;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.ObjectAlreadyExists;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.exceptions.ProblemForPersists;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Category;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.models.Product;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.ICategory;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.repositories.IProduct;
import proyecto_final_backend.ecommerce.kevin_vittor.proyecto_final.services.IProductService;

@Log4j2
@Service
public class ProductService implements IProductService{

    @Autowired
    private IProduct productRepository;
    
    @Autowired
    private ICategory categoryRepository;

    @Override
    @Transactional
    public void delete(Product object) {
       try{
            Product productDb = findById(object.getId()).orElseThrow(() -> new NotFoundException("El producto con ese id no existe"));
            productRepository.delete(productDb);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede eliminar el producto"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try{
            Product productDb = findById(id).orElseThrow(() -> new NotFoundException("El producto con ese id no existe"));
            productRepository.deleteById(productDb.getId());
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede eliminar el producto con esa id"); // add customs exceptions
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        try{
            return productRepository.findAll();
        }catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Integer id) {
        try{
            return productRepository.findById(id);
        }catch(RuntimeException e){
            throw e; // add customs exceptions
        }
    }

    @Override
    @Transactional
    public Product save(Product object) {
        try{
            return productRepository.save(object);
        }catch(RuntimeException e){
            throw new ProblemForPersists("No se puede actualizar / guardar el producto"); // add customs exceptions
        }
    }

    @Override
    @Transactional
    public Product saveObject(Product product) {
        if(product.getId() == null){
            log.info("Producto: " + product);
            Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new NotFoundException("No existe la categoria con ese nombre"));
            product.setCategory(category);
            return save(product);
        }
        throw new ObjectAlreadyExists("El id ya existe y es: " + product.getId()); // add customs exceptions
        // add custom exceptions!!!!
    }

    @Override
    @Transactional
    public Product updateObject(Integer id, Product product) {
        if(id != null){
            Product productDb = findById(id).orElseThrow(() -> new NotFoundException("No existe el producto con ese id!"));
            setProduct(product, productDb);
            return save(productDb);
        }
        throw new NotFoundException("No existe el id, no puedes actualizarlo"); // add custom exceptions!!!!;
    }

    private void setProduct(Product newProduct, Product productDb) {
        productDb.setName(newProduct.getName());
        productDb.setPrice(newProduct.getPrice());
        productDb.setStock(newProduct.getStock());

        if (newProduct.getCategory() != null && newProduct.getCategory().getId() != null) {
            categoryRepository.findById(newProduct.getCategory().getId()).ifPresent(productDb::setCategory);
        }
    }

}
