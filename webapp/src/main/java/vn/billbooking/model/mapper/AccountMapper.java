package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.AccountDTO;
import vn.billbooking.model.entity.Account;

import java.util.List;

public interface AccountMapper {

    // Map Entity to DTO
    AccountDTO toDTO(Account account);

    List<AccountDTO> toListDTO(List<Account> accounts);

    // Map DTO to Entity
    Account toEntity(Account account, AccountDTO accountDTO);

}
