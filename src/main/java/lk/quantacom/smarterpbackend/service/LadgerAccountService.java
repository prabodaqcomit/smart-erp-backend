package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.LadgerAccountRequest;
import lk.quantacom.smarterpbackend.dto.request.LadgerAccountUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LadgerAccountResponse;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LadgerAccountService {

    LadgerAccountResponse save(LadgerAccountRequest request);

    LadgerAccountResponse update(LadgerAccountUpdateRequest request);

    LadgerAccountResponse getById(Long id);

    LadgerAccountResponse getByAccNo(String accNo);

    List<LadgerAccountResponse> getAll();

    List<LadgerAccountResponse> getAllByCategory(String category,String name);

    List<LadgerAccountResponse> getAllBySubAccountType(String subAccountType);

    Integer delete(Long id);

    List<LadgerAccountResponse> getByAccName(String accName);

    List<LadgerAccountResponse> getByAccountCategoryAndAccType(String accName);

    List<LadgerAccountResponse> getByAccountCategoryAndAccName(String accName);

    List<LadgerAccountResponse> getByAccountCategoryAssetAndAccName(String accName);

    List<LadgerAccountResponse> getByAccountCategoryExpenseAndAccName(String accName);

    //List<LadgerAccountResponse> getAllByNameLike(String accName);

}