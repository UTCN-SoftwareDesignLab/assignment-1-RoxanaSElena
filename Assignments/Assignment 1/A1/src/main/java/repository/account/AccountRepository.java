package repository.account;

import java.util.List;
import model.Account;
public interface AccountRepository {

    List<Account> findAll();
    Account findByClientId (int idClient);
    Account findByClientCNP(String cnp);
    Account findById(int id);
    boolean save(Account account);
    boolean removeById (int id);
    boolean update (Account account);
    void removeAll();

}
