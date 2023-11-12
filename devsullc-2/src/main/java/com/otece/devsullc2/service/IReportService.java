package com.otece.devsullc2.service;

import java.time.LocalDate;
import java.util.List;

import com.otece.devsullc2.dto.AccountReportDTO;
import com.otece.devsullc2.exception.MovementException;

public interface IReportService {
	List<AccountReportDTO> generateReport(LocalDate startDate, LocalDate endDate, String clientId) throws MovementException;
}
