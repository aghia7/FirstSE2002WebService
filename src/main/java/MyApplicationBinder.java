import data.PostgresDB;
import data.interfaces.IDB;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import repositories.ProductRepository;
import repositories.UserRepository;
import repositories.interfaces.IProductRepository;
import repositories.interfaces.IUserRepository;
import services.AuthService;
import services.ProductService;
import services.UserService;
import services.interfaces.IAuthService;
import services.interfaces.IProductService;
import services.interfaces.IUserService;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PostgresDB.class).to(IDB.class);
        bind(UserRepository.class).to(IUserRepository.class);
        bind(ProductRepository.class).to(IProductRepository.class);
        bind(ProductService.class).to(IProductService.class);
        bind(UserService.class).to(IUserService.class);
        bind(AuthService.class).to(IAuthService.class);
    }
}