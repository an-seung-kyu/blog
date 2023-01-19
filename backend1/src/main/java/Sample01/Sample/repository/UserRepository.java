package Sample01.Sample.repository;

import Sample01.Sample.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findUsersByUserId (String userId);
}
