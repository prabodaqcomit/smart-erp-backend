package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.RoleSaveRequest;
import lk.quantacom.smarterpbackend.dto.request.UserHeadRequest;
import lk.quantacom.smarterpbackend.dto.request.UserHeadUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UserHeadResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.Role;
import lk.quantacom.smarterpbackend.entity.UserHead;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.RoleRepository;
import lk.quantacom.smarterpbackend.repository.UserHeadRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.UserHeadService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userService")
@Slf4j
public class UserHeadServiceImpl implements UserDetailsService,UserHeadService {

    @Autowired
    private UserHeadRepository userHeadRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private RoleRepository roleRepository;

    private void saveLog(String form,String action){
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional
    public UserHeadResponse save(UserHeadRequest request) {

        UserHead userHead = new UserHead();
        userHead.setFldUserId(request.getFldUserId());
        userHead.setFldTitle(request.getFldTitle());
        userHead.setFldFName(request.getFldFName());
        userHead.setFldLName(request.getFldLName());
        userHead.setFldAddress(request.getFldAddress());
        userHead.setFldTel(request.getFldTel());
        userHead.setFldEmail(request.getFldEmail());

        userHead.setFldPassword(bcryptEncoder.encode(request.getFldPassword()));

        userHead.setFldPwChangedDate(request.getFldPwChangedDate()==null? null:ConvertUtils.convertStrToDate(request.getFldPwChangedDate()));
        userHead.setFldCanCopy(request.getFldCanCopy());
        userHead.setFldCanPrint(request.getFldCanPrint());
        userHead.setFldEnabled(request.getFldEnabled());
        userHead.setFldPwChangeOnNextLogon(request.getFldPwChangeOnNextLogon());
        userHead.setFldUserCategory(request.getFldUserCategory());
        userHead.setFldSignedOn(request.getFldSignedOn());
        userHead.setFldSignedOff(request.getFldSignedOff());
        userHead.setFldStationId(request.getFldStationId());
        userHead.setFldSignOndate(request.getFldSignOndate()==null? null:ConvertUtils.convertStrToDate(request.getFldSignOndate()));
        userHead.setFldFloat(request.getFldFloat());
        userHead.setFldManagerSignedOff(request.getFldManagerSignedOff());
        userHead.setFldUserGroup(request.getFldUserGroup());
        userHead.setFldUserhedShiftno(request.getFldUserhedShiftno());
        userHead.setIsDeleted(Deleted.NO);
        userHead.setRoles(roleRepository.findAll());

        BranchNetwork branchNetwork=new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        userHead.setBranch(branchNetwork);

        UserHead save = userHeadRepository.save(userHead);

        saveLog("UserHead","Data Saved - "+save.getId());

        return convert(save);
    }

    private static List<Role> convertRole(Set<RoleSaveRequest> roles) {
        if (roles == null) {
            return null;
        }
        List<Role> roleList = new ArrayList<>();
        for (RoleSaveRequest roleSaveRequest : roles) {
            Role role = new Role();
            role.setId(roleSaveRequest.getId());
            role.setName(roleSaveRequest.getName());
            role.setDescription(roleSaveRequest.getDescription());
            roleList.add(role);
        }

        return roleList;
    }

    @Override
    @Transactional
    public UserHeadResponse update(UserHeadUpdateRequest request) {

        UserHead userHead = userHeadRepository.findById(request.getId()).orElse(null);
        if(userHead==null){
            return null;
        }

        userHead.setId(request.getId());
        userHead.setFldUserId(request.getFldUserId());
        userHead.setFldTitle(request.getFldTitle());
        userHead.setFldFName(request.getFldFName());
        userHead.setFldLName(request.getFldLName());
        userHead.setFldAddress(request.getFldAddress());
        userHead.setFldTel(request.getFldTel());
        userHead.setFldEmail(request.getFldEmail());

        if(!request.getFldPassword().isEmpty()){
            userHead.setFldPassword(bcryptEncoder.encode(request.getFldPassword()));
        }

        userHead.setFldPwChangedDate(request.getFldPwChangedDate()==null? null:ConvertUtils.convertStrToDate(request.getFldPwChangedDate()));
        userHead.setFldCanCopy(request.getFldCanCopy());
        userHead.setFldCanPrint(request.getFldCanPrint());
        userHead.setFldEnabled(request.getFldEnabled());
        userHead.setFldPwChangeOnNextLogon(request.getFldPwChangeOnNextLogon());
        userHead.setFldUserCategory(request.getFldUserCategory());
        userHead.setFldSignedOn(request.getFldSignedOn());
        userHead.setFldSignedOff(request.getFldSignedOff());
        userHead.setFldStationId(request.getFldStationId());
        userHead.setFldSignOndate(request.getFldSignOndate()==null? null:ConvertUtils.convertStrToDate(request.getFldSignOndate()));
        userHead.setFldFloat(request.getFldFloat());
        userHead.setFldManagerSignedOff(request.getFldManagerSignedOff());
        userHead.setFldUserGroup(request.getFldUserGroup());
        userHead.setFldUserhedShiftno(request.getFldUserhedShiftno());

        BranchNetwork branchNetwork=new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        userHead.setBranch(branchNetwork);

        UserHead updated=userHeadRepository.save(userHead);

        saveLog("UserHead","Data Updated - "+updated.getId());

        return (convert(updated));
    }

    @Override
    public UserHeadResponse getById(Long id) {

        return userHeadRepository.findById(id).map(UserHeadServiceImpl::convert).orElse(null);
    }

    @Override
    @Transactional
    public UserHeadResponse getByUn(String user) {

        UserHead userH=userHeadRepository.findByFldUserId(user);

        if(userH!=null){
            saveLog("UserHead","Logged In - "+user);
            return convert(userH);
        }



        return null;

    }

    @Override
    public List<UserHeadResponse> getAll() {

        return userHeadRepository.findAll()
                .stream().map(UserHeadServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        UserHead user=userHeadRepository.findById(id).orElse(null);
        if(user==null){
            return 0;
        }
        user.setIsDeleted(Deleted.YES);
        userHeadRepository.save(user);

        return  1;

    }

    private static UserHeadResponse convert(UserHead userHead) {

        UserHeadResponse typeResponse = new UserHeadResponse();
        typeResponse.setFldUserId(userHead.getFldUserId());
        typeResponse.setFldTitle(userHead.getFldTitle());
        typeResponse.setFldFName(userHead.getFldFName());
        typeResponse.setFldLName(userHead.getFldLName());
        typeResponse.setFldAddress(userHead.getFldAddress());
        typeResponse.setFldTel(userHead.getFldTel());
        typeResponse.setFldEmail(userHead.getFldEmail());
        //typeResponse.setFldPassword(userHead.getFldPassword());
        typeResponse.setFldPwChangedDate(ConvertUtils.convertDateToStr(userHead.getFldPwChangedDate()));
        typeResponse.setFldCanCopy(userHead.getFldCanCopy());
        typeResponse.setFldCanPrint(userHead.getFldCanPrint());
        typeResponse.setFldEnabled(userHead.getFldEnabled());
        typeResponse.setFldPwChangeOnNextLogon(userHead.getFldPwChangeOnNextLogon());
        typeResponse.setFldUserCategory(userHead.getFldUserCategory());
        typeResponse.setFldSignedOn(userHead.getFldSignedOn());
        typeResponse.setFldSignedOff(userHead.getFldSignedOff());
        typeResponse.setFldStationId(userHead.getFldStationId());
        typeResponse.setFldSignOndate(ConvertUtils.convertDateToStr(userHead.getFldSignOndate()));
        typeResponse.setFldFloat(userHead.getFldFloat());
        typeResponse.setFldManagerSignedOff(userHead.getFldManagerSignedOff());
        typeResponse.setFldUserGroup(userHead.getFldUserGroup());
        typeResponse.setFldUserhedShiftno(userHead.getFldUserhedShiftno());
        typeResponse.setBranchId(userHead.getBranch().getId());
        //typeResponse.setRoleName(userHead.getRoles().get(0).getName());

        typeResponse.setId(userHead.getId());
        typeResponse.setCreatedBy(userHead.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(userHead.getCreatedDateTime()));
        typeResponse.setModifiedBy(userHead.getModifiedBy());
        typeResponse.setIsDeleted(userHead.getIsDeleted());

        return typeResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserHead user = userHeadRepository.findByFldUserId(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getFldUserId(), user.getFldPassword(),getAuthority(user));

    }

    private Set<SimpleGrantedAuthority> getAuthority(UserHead user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
}