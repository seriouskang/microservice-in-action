package com.example.rental.application.usecase;

import com.example.rental.framework.httpadapter.dto.ClearOverdueInfoDTO;
import com.example.rental.framework.httpadapter.dto.RentalResultOutputDTO;

public interface ClearOverdueUsecase {
    RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO);
}
