package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LadgerAccountRequest;
import lk.quantacom.smarterpbackend.dto.request.LadgerAccountUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LadgerAccountResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lk.quantacom.smarterpbackend.repository.LadgerAccountRepository;
import lk.quantacom.smarterpbackend.service.LadgerAccountService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LadgerAccountServiceImpl implements LadgerAccountService {

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public LadgerAccountResponse save(LadgerAccountRequest request) {

//        LadgerAccount account = ladgerAccountRepository.findByAccNo(request.getAccNo());
//
//        if (account==null){
            LadgerAccount ladgerAccount = new LadgerAccount();
            ladgerAccount.setAccountCategory(request.getAccountCategory());
            ladgerAccount.setAccType(request.getAccType());
            ladgerAccount.setSubAccType(request.getSubAccType());
            ladgerAccount.setAccNo(request.getAccNo());
            ladgerAccount.setAccName(request.getAccName());
            ladgerAccount.setObBalance(request.getObBalance());
            ladgerAccount.setCurrentBalance(request.getCurrentBalance());
            ladgerAccount.setGeneratedNo(request.getGeneratedNo());
            ladgerAccount.setOwnNo(request.getOwnNo());
            ladgerAccount.setIsDefault(request.getIsDefault());
            ladgerAccount.setIsDeleted(Deleted.NO);
            if (request.getAmount()<0){
                ladgerAccount.setDrCr("Credit");
            }else {
                ladgerAccount.setDrCr("Debit");
            }
            ladgerAccount.setAmount(request.getAmount());
            BranchNetwork branch=new BranchNetwork();
            branch.setId(request.getBranchId());
            ladgerAccount.setBranch(branch);
            ladgerAccount.setStatus(Status.ACTIVE);
            ladgerAccount.setAccDate(ConvertUtils.convertStrToDate(request.getAccDate()));

            LadgerAccount save = ladgerAccountRepository.save(ladgerAccount);

            saveLog("LadgerAccount", "Data Saved - " + save.getId());

            return convert(save);
//        }else {
//            return null;
//        }

    }

    @Override
    @Transactional
    public LadgerAccountResponse update(LadgerAccountUpdateRequest request) {

        LadgerAccount ladgerAccount = ladgerAccountRepository.findById(request.getId()).orElse(null);
        if (ladgerAccount == null) {
            return null;
        }

        ladgerAccount.setId(request.getId());
        ladgerAccount.setAccountCategory(request.getAccountCategory());
        ladgerAccount.setAccType(request.getAccType());
        ladgerAccount.setSubAccType(request.getSubAccType());
        ladgerAccount.setAccNo(request.getAccNo());
        ladgerAccount.setAccName(request.getAccName());
        ladgerAccount.setObBalance(request.getObBalance());
        ladgerAccount.setCurrentBalance(request.getCurrentBalance());
        ladgerAccount.setGeneratedNo(request.getGeneratedNo());
        ladgerAccount.setOwnNo(request.getOwnNo());
        ladgerAccount.setIsDefault(request.getIsDefault());
        BranchNetwork branch=new BranchNetwork();
        branch.setId(request.getBranchId());
        ladgerAccount.setBranch(branch);
        ladgerAccount.setDrCr(request.getDrCr());
        ladgerAccount.setAmount(request.getAmount());
        ladgerAccount.setAccDate(ConvertUtils.convertStrToDate(request.getAccDate()));
        ladgerAccount.setStatus(request.getStatus() ? Status.ACTIVE:Status.INACTIVE);

        LadgerAccount updated = ladgerAccountRepository.save(ladgerAccount);

        saveLog("LadgerAccount", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public LadgerAccountResponse getById(Long id) {

        return ladgerAccountRepository.findById(id).map(LadgerAccountServiceImpl::convert).orElse(null);
    }

    @Override
    public LadgerAccountResponse getByAccNo(String accNo) {
        LadgerAccount acc=ladgerAccountRepository.findByAccNo(accNo);
        if(acc==null){
            return  null;
        }
        return convert(acc);
    }

    @Override
    public List<LadgerAccountResponse> getAll() {

        return ladgerAccountRepository.getAllByDesc()
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<LadgerAccountResponse> getAllBySubAccountType(String subAccountType) {
        return ladgerAccountRepository.
                findByIsDeletedAndSubAccType(Deleted.NO, subAccountType)
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LadgerAccountResponse> getAllByCategory(String category, String name) {
        return ladgerAccountRepository.
                findByIsDeletedAndAccountCategoryOrderByAccNoDesc(Deleted.NO,category)
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        LadgerAccount got = ladgerAccountRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ladgerAccountRepository.save(got);

        saveLog("LadgerAccount", "Data Deleted - " + id);

        return 1;
    }

    @Override
    public List<LadgerAccountResponse> getByAccName(String accName) {
        return ladgerAccountRepository.
                findByAccNameOrderByAccNoDesc(accName)
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LadgerAccountResponse> getByAccountCategoryAndAccType(String accName) {
        return ladgerAccountRepository.
                findByAccountCategoryAndAccTypeAndAccNameOrderByAccNoDesc("INCOME","INCOME",accName)
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LadgerAccountResponse> getByAccountCategoryAndAccName(String accName) {
        return ladgerAccountRepository.
                findByAccountCategoryAndAccNameOrderByAccNoDesc("OTHER CURRENT ASSET",accName)
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LadgerAccountResponse> getByAccountCategoryAssetAndAccName(String accName) {
        return ladgerAccountRepository.
                findByAccountCategoryAndAccNameOrderByAccNoDesc("ASSETS",accName)
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<LadgerAccountResponse> getByAccountCategoryExpenseAndAccName(String accName) {
        return ladgerAccountRepository.
                findByAccountCategoryAndAccNameOrderByAccNoDesc("EXPENSE",accName)
                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
    }

//    @Override
//    public List<LadgerAccountResponse> getAllByNameLike(String accName) {
//        return ladgerAccountRepository.
//                //findByIsDeletedAndAccNameContaining( Deleted.NO, accName)
//                        doFindByIsDeletedAndAccNameContaining(accName)
//                .stream().map(LadgerAccountServiceImpl::convert).collect(Collectors.toList());
//    }

    public static LadgerAccountResponse convert(LadgerAccount ladgerAccount) {

        LadgerAccountResponse typeResponse = new LadgerAccountResponse();
        typeResponse.setAccountCategory(ladgerAccount.getAccountCategory());
        typeResponse.setAccType(ladgerAccount.getAccType());
        typeResponse.setSubAccType(ladgerAccount.getSubAccType());
        typeResponse.setAccNo(ladgerAccount.getAccNo());
        typeResponse.setAccName(ladgerAccount.getAccName());
        typeResponse.setObBalance(ladgerAccount.getObBalance());
        typeResponse.setCurrentBalance(ladgerAccount.getCurrentBalance());
        typeResponse.setGeneratedNo(ladgerAccount.getGeneratedNo());
        typeResponse.setOwnNo(ladgerAccount.getOwnNo());
        typeResponse.setIsDefault(ladgerAccount.getIsDefault());
        typeResponse.setBranchId(ladgerAccount.getBranch().getId());
        typeResponse.setBranchName(ladgerAccount.getBranch().getBranchName());
        typeResponse.setId(ladgerAccount.getId());
        typeResponse.setCreatedBy(ladgerAccount.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ladgerAccount.getCreatedDateTime()));
        typeResponse.setModifiedBy(ladgerAccount.getModifiedBy());
        typeResponse.setIsDeleted(ladgerAccount.getIsDeleted());
        typeResponse.setDrCr(ladgerAccount.getDrCr());
        typeResponse.setAmount(ladgerAccount.getAmount());
        typeResponse.setStatus(ladgerAccount.getStatus());
        typeResponse.setAccDate(ConvertUtils.convertDateToStr(ladgerAccount.getAccDate()));

        return typeResponse;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

}