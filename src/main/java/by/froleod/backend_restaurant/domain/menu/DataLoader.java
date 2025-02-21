package by.froleod.backend_restaurant.domain.menu;


import by.froleod.backend_restaurant.domain.menu.entity.Product;
import by.froleod.backend_restaurant.domain.menu.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product("Пицца Маргарита", 500, "Классическая пицца с томатами и моцареллой", "https://www.chefmarket.ru/blog/wp-content/uploads/2020/10/pizza-margherita--2000x1200.jpg"));
        productRepository.save(new Product("Паста Карбонара", 450, "Паста с беконом, яйцом и пармезаном", "https://s1.eda.ru/StaticContent/Photos/Upscaled/150525210126/150601174518/p_O.jpg"));
    }
}
