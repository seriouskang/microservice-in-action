package com.example.rental.framework.httpadapter;

import com.example.rental.application.port.in.*;
import com.example.rental.framework.httpadapter.dto.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
@RequiredArgsConstructor
@Api(tags = {"대여 컨트롤러"})
public class RentalController {
    private final RentUsecase rentUsecase;
    private final SubmitUsecase submitUsecase;
    private final CreateRentalCardUsecase createRentalCardUsecase;
    private final OverdueUsecase overdueUsecase;
    private final ClearOverdueUsecase clearOverdueUsecase;
    private final InquiryUsecase inquiryUsecase;

    @PostMapping("/rental-card")
    public ResponseEntity<RentalCardOutputDTO> createRentalCard(@RequestBody UserInputDTO userInputDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createRentalCardUsecase.createRentalCard(userInputDTO));
    }

    @GetMapping("/rental-card/{userId}")
    public ResponseEntity<RentalCardOutputDTO> rentalCard(@PathVariable Long userId) {
        Optional<RentalCardOutputDTO> rentalCard = inquiryUsecase.getRentalCard(new UserInputDTO(userId, ""));
        return ResponseEntity
                .ok(rentalCard.get());  // @TODO: validate
    }

    @GetMapping("/{userId}/rental-items")
    public ResponseEntity<List<RentalItemOutputDTO>> allRentalItems(@PathVariable Long userId) {
        Optional<List<RentalItemOutputDTO>> allRentalItems = inquiryUsecase.getAllRentalItems(new UserInputDTO(userId, ""));
        return ResponseEntity
                .ok(allRentalItems.get());  // @TODO: validate
    }

    @GetMapping("/{userId}/submit-items")
    public ResponseEntity<List<SubmitItemOutputDTO>> allSubmitItems(@PathVariable Long userId) {
        Optional<List<SubmitItemOutputDTO>> allSubmitItems = inquiryUsecase.getAllSubmitItems(new UserInputDTO(userId, ""));
        return ResponseEntity
                .ok(allSubmitItems.get());  // @TODO: validate
    }

    @PostMapping("/rent")
    public ResponseEntity<RentalCardOutputDTO> rent(@RequestBody UserItemInputDTO userItemInputDTO) {
        RentalCardOutputDTO rentalCardOutputDTO = rentUsecase.rent(userItemInputDTO);
        return ResponseEntity
                .ok(rentalCardOutputDTO);
    }

    @PostMapping("/submit")
    public ResponseEntity<RentalCardOutputDTO> submit(@RequestBody UserItemInputDTO userItemInputDTO) {
        RentalCardOutputDTO rentalCardOutputDTO = submitUsecase.submit(userItemInputDTO);
        return ResponseEntity
                .ok(rentalCardOutputDTO);
    }

    @PostMapping("/overdue")
    public ResponseEntity<RentalCardOutputDTO> overdue(@RequestBody UserItemInputDTO userItemInputDTO) {
        RentalCardOutputDTO rentalCardOutputDTO = overdueUsecase.overdue(userItemInputDTO);
        return ResponseEntity
                .ok(rentalCardOutputDTO);
    }

    @PostMapping("/clearoverdue")
    public ResponseEntity<RentalResultOutputDTO> clearOverDue(@RequestBody ClearOverdueInfoDTO clearOverdueInfoDTO) {
        RentalResultOutputDTO rentalResultOutputDTO = clearOverdueUsecase.clearOverdue(clearOverdueInfoDTO);
        return ResponseEntity
                .ok(rentalResultOutputDTO);
    }
}
