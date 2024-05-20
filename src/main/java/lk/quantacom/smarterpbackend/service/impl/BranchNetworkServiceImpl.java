package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BranchNetworkRequest;
import lk.quantacom.smarterpbackend.dto.request.BranchNetworkUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BranchNetworkResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.BranchType;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.BranchNetworkRepository;
import lk.quantacom.smarterpbackend.repository.BranchTypeRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.BranchNetworkService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchNetworkServiceImpl implements BranchNetworkService {

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private BranchTypeRepository branchTypeRepository;

    static BranchNetworkResponse convert(BranchNetwork branchNetwork) {

        BranchNetworkResponse typeResponse = new BranchNetworkResponse();
        typeResponse.setBranchId(branchNetwork.getBranchId());
        typeResponse.setBranchName(branchNetwork.getBranchName());
        typeResponse.setFldLocation(branchNetwork.getFldLocation());
        typeResponse.setFldProvince(branchNetwork.getFldProvince());
        typeResponse.setBranchLevelId(branchNetwork.getBranchLevelId());
        typeResponse.setId(branchNetwork.getId());
        typeResponse.setCreatedBy(branchNetwork.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(branchNetwork.getCreatedDateTime()));
        typeResponse.setModifiedBy(branchNetwork.getModifiedBy());
        typeResponse.setIsDeleted(branchNetwork.getIsDeleted());
        typeResponse.setIsPublicBranch(branchNetwork.getIsPublicBranch() == null || branchNetwork.getIsPublicBranch());
        typeResponse.setDamageLocation(branchNetwork.getDamageLocation() != null && branchNetwork.getDamageLocation());
        typeResponse.setBranchType(branchNetwork.getBranchType());


        return typeResponse;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BranchNetworkResponse save(BranchNetworkRequest request) {

//        Integer count = branchNetworkRepository.getBranchCount();

//        if (count >= 2) {
//            return null;
//        } else {
            String newMax = "00001";
            String max = branchNetworkRepository.getMaxId();
            if (max != null) {
                double exMax = Double.parseDouble(max);
                newMax = new DecimalFormat("00000").format(exMax + 1);
            }

            if (request.getDamageLocation()) {
                List<BranchNetwork> branchNetwork = branchNetworkRepository.findByIsDeleted(Deleted.NO);
                for (BranchNetwork branchNetwork1 : branchNetwork) {
                    branchNetwork1.setDamageLocation(false);
                    branchNetworkRepository.save(branchNetwork1);
                }
            }

            BranchNetwork branchNetwork = new BranchNetwork();
            branchNetwork.setBranchId(newMax);
            BranchType branchType = branchTypeRepository.getById(request.getBranchType());
            if (request.getBranchType() != null) {
                branchNetwork.setBranchName(request.getBranchName().trim() + " - " + branchType.getName());
            }
//        if (request.getBranchType().equals("o")){
//
//        }else if (request.getBranchType().equals("s")){
//            branchNetwork.setBranchName(request.getBranchName().trim()+" - Store");
//        }else if (request.getBranchType().equals("b")){
//            branchNetwork.setBranchName(request.getBranchName().trim()+" - Branch");
//        }else {
//            branchNetwork.setBranchName(request.getBranchName().trim());
//        }
            branchNetwork.setFldLocation(request.getFldLocation());
            branchNetwork.setFldProvince(request.getFldProvince());
            branchNetwork.setBranchLevelId(request.getBranchLevelId());
            branchNetwork.setBranchType(request.getBranchType());


            branchNetwork.setIsPublicBranch(request.getIsPublicBranch());
            branchNetwork.setDamageLocation(request.getDamageLocation());

            branchNetwork.setIsDeleted(Deleted.NO);
            BranchNetwork save = branchNetworkRepository.save(branchNetwork);

            saveLog("BranchNetwork", "Data Saved - " + save.getId());

            return convert(save);
//        }


    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional
    public BranchNetworkResponse update(BranchNetworkUpdateRequest request) {

//        Integer count = branchNetworkRepository.getBranchCount();
//
//        if (count >= 2) {
//            return null;
//        } else {
            BranchNetwork branchNetwork = branchNetworkRepository.findById(request.getId()).orElse(null);
            if (branchNetwork == null) {
                return null;
            }

            if (request.getDamageLocation()) {
                List<BranchNetwork> branchNetworks = branchNetworkRepository.findByIsDeleted(Deleted.NO);
                for (BranchNetwork branchNetwork1 : branchNetworks) {
                    branchNetwork1.setDamageLocation(false);
                    branchNetworkRepository.save(branchNetwork1);
                }
            }

            branchNetwork.setId(request.getId());
            //branchNetwork.setBranchId(request.getBranchId());
            branchNetwork.setBranchType(request.getBranchType());
            branchNetwork.setBranchName(request.getBranchName().trim());
            branchNetwork.setFldLocation(request.getFldLocation());
            branchNetwork.setFldProvince(request.getFldProvince());
            branchNetwork.setBranchLevelId(request.getBranchLevelId());
            branchNetwork.setIsPublicBranch(request.getIsPublicBranch());
            branchNetwork.setDamageLocation(request.getDamageLocation());
            branchNetwork.setIsDeleted(request.getIsDeleted());

            BranchNetwork updated = branchNetworkRepository.save(branchNetwork);

            saveLog("BranchNetwork", "Data Updated - " + updated.getId());

            return (convert(updated));
//        }

    }

    @Override
    public BranchNetworkResponse getById(Long id) {

        return branchNetworkRepository.findById(id).map(BranchNetworkServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BranchNetworkResponse> getAll() {

        return branchNetworkRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BranchNetworkServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<BranchNetworkResponse> getAllPublic() {

        List<BranchNetworkResponse> res = new ArrayList<>();

        List<BranchNetwork> responses = branchNetworkRepository.findByIsDeleted(Deleted.NO);
        for (BranchNetwork br : responses) {
            if (br.getIsPublicBranch() == null || br.getIsPublicBranch()) {
                res.add(convert(br));
            }
        }
        return res;
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        BranchNetwork got = branchNetworkRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        branchNetworkRepository.save(got);

        saveLog("BranchNetwork", "Data Deleted - " + id);

        return 1;
    }

    @Override
    public List<BranchNetworkResponse> getByBranchName(String branchName) {
        return branchNetworkRepository.findByBranchName(branchName)
                .stream().map(BranchNetworkServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<BranchNetworkResponse> getByBranchType(Long branchType) {
        return branchNetworkRepository.findByBranchType(branchType)
                .stream().map(BranchNetworkServiceImpl::convert).collect(Collectors.toList());
    }

}