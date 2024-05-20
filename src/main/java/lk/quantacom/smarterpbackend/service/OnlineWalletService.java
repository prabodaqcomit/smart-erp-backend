package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.OnlineWalletRequest;
import lk.quantacom.smarterpbackend.dto.request.OnlineWalletUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OnlineWalletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OnlineWalletService {

    OnlineWalletResponse save(OnlineWalletRequest request);

    OnlineWalletResponse update(OnlineWalletUpdateRequest request);

    OnlineWalletResponse getById(Long id);

    List<OnlineWalletResponse> getAll();


    Integer delete(Long id);
}