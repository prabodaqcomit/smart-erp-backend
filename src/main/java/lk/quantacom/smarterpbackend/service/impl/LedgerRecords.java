package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerCommonSelectionsRequest;
import lk.quantacom.smarterpbackend.objects.ClsLedgerCommonSelections;

import java.util.ArrayList;
import java.util.List;

public class LedgerRecords {

    public List<LedgerCommonSelectionsRequest> getCommonLedgerList(String ledgerId,String ledgerAccNum,String type){
        List<LedgerCommonSelectionsRequest> selectionsRequestList = new ArrayList<>();

        if(type.equals("customer")){
            ClsLedgerCommonSelections clcs1 = new ClsLedgerCommonSelections("M144", "CUSTOMER CHEQUE RETURNS", "return a customer cheque", "3", ledgerId, ledgerAccNum, "0", "0", "C");
            ClsLedgerCommonSelections clcs2 = new ClsLedgerCommonSelections("M022", "Invoice (Bill Discount)", "cash discount", "1", "7", "5021212003", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs3 = new ClsLedgerCommonSelections("M038", "Invoice (Credit Customer Sales)", "Credit invoice", "11", ledgerId, ledgerAccNum, "2", "002", "C");
            ClsLedgerCommonSelections clcs4 = new ClsLedgerCommonSelections("M022", "Invoice (Bill Discount)", "credit discount", "11", "7", "5021212003", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs5 = new ClsLedgerCommonSelections("M138", "Payment Receipt", "Receiving a cash payment form customer", "1", "1", "001", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs6 = new ClsLedgerCommonSelections("M138", "Payment Receipt", "giving a cash value discount to a customer", "1", "1", "001", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs7 = new ClsLedgerCommonSelections("M138", "Payment Receipt", "giving a cash presentage discount to a customer", "1", "1", "001", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs8 = new ClsLedgerCommonSelections("M138", "Payment Receipt", "Deposit a customer cheque on bank another day", "3", "8", "10011150001", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs9 = new ClsLedgerCommonSelections("M138", "Payment Receipt", "Deposit a customer cheque on bank same day", "3", "0", "0", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs10 = new ClsLedgerCommonSelections("M138", "Payment Receipt", "forgott paying amout from deptor", "1", "11", "50130804", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs11 = new ClsLedgerCommonSelections("M006", "Invoice return note (Sales Retur)", "return a cash purchase items from cash customer", "1", "1", "001", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs12 = new ClsLedgerCommonSelections("M006", "Invoice return note (Sales Retur)", "return a cash purchase items from credit customer", "11", "10", "40111160001", ledgerId, ledgerAccNum, "C");
            ClsLedgerCommonSelections clcs13 = new ClsLedgerCommonSelections("M230", "CUSTOMER OPENNING BALANCE", "new opening balance of a customer", "1", ledgerId, ledgerAccNum, "2", "002", "C");

            ClsLedgerCommonSelections[] CommonAccountArray = new ClsLedgerCommonSelections[13];
            CommonAccountArray[0] = clcs1;
            CommonAccountArray[1] = clcs2;
            CommonAccountArray[2] = clcs3;
            CommonAccountArray[3] = clcs4;
            CommonAccountArray[4] = clcs5;
            CommonAccountArray[5] = clcs6;
            CommonAccountArray[6] = clcs7;
            CommonAccountArray[7] = clcs8;
            CommonAccountArray[8] = clcs9;
            CommonAccountArray[9] = clcs10;
            CommonAccountArray[10] = clcs11;
            CommonAccountArray[11] = clcs12;
            CommonAccountArray[12] = clcs13;

            for (ClsLedgerCommonSelections CommonAccountArray1 : CommonAccountArray) {


                LedgerCommonSelectionsRequest ledgerCommonSelections = new LedgerCommonSelectionsRequest(
                        CommonAccountArray1.getCredit_acc_id_STRING() == null ?
                                0 : Integer.parseInt(CommonAccountArray1.getCredit_acc_id_STRING()),
                        CommonAccountArray1.getCredit_acc_num_STRING()==null?
                                "0":CommonAccountArray1.getCredit_acc_num_STRING(),
                        CommonAccountArray1.getDebit_acc_id_STRING() == null ?
                                0 : Integer.parseInt(CommonAccountArray1.getDebit_acc_id_STRING()),
                        CommonAccountArray1.getDebit_acc_num_STRING() == null ?
                                "0" : CommonAccountArray1.getDebit_acc_num_STRING(),
                        CommonAccountArray1.getDescription_STRING(),
                        CommonAccountArray1.getFram_name_STRING(),
                        CommonAccountArray1.getFrame_id_STRING(),
                        CommonAccountArray1.getPay_mode_STRING(),
                        CommonAccountArray1.getRecord_right_STRING()
                );
                selectionsRequestList.add(ledgerCommonSelections);

            }
        }else if (type.equals("supplier")){
            ClsLedgerCommonSelections clcs1 = new ClsLedgerCommonSelections("M123", "G.R.N", "cash discount", "1", ledgerId, ledgerAccNum, "6", "4019148004", "S");
            ClsLedgerCommonSelections clcs2 = new ClsLedgerCommonSelections("M123", "G.R.N", "credit goods purchase", "11", "4", "004", ledgerId, ledgerAccNum, "S");
            ClsLedgerCommonSelections clcs3 = new ClsLedgerCommonSelections("M123", "G.R.N", "credit discount", "11", ledgerId, ledgerAccNum, "6", "4019148004", "S");
            ClsLedgerCommonSelections clcs4 = new ClsLedgerCommonSelections("M157", "SUPPLIER PAYMENTS", "Paying from cash", "1", ledgerId, ledgerAccNum, "1", "001", "S");
            ClsLedgerCommonSelections clcs5 = new ClsLedgerCommonSelections("M157", "SUPPLIER PAYMENTS", "Received a discount from a supplier as a money discount", "1", ledgerId, ledgerAccNum, "1", "001", "S");
            ClsLedgerCommonSelections clcs6 = new ClsLedgerCommonSelections("M157", "SUPPLIER PAYMENTS", "Received a discount from a supplier as a presentage discount", "1", ledgerId, ledgerAccNum, "1", "001", "S");
            ClsLedgerCommonSelections clcs7 = new ClsLedgerCommonSelections("M157", "SUPPLIER PAYMENTS", "Paying a amount from a customer cheque", "3", ledgerId, ledgerAccNum, "8", "10011150001", "S");
            ClsLedgerCommonSelections clcs8 = new ClsLedgerCommonSelections("M157", "SUPPLIER PAYMENTS", "Paying a amount from a own cheque", "3", ledgerId, ledgerAccNum, "0", "0", "S");
            ClsLedgerCommonSelections clcs9 = new ClsLedgerCommonSelections("M089", "Purchase Received Note", "cash discount", "1", ledgerId, ledgerAccNum, "6", "4019148004", "S");
            ClsLedgerCommonSelections clcs10 = new ClsLedgerCommonSelections("M089", "Purchase Received Note", "credit goods purchase", "11", "4", "004", ledgerId, ledgerAccNum, "S");
            ClsLedgerCommonSelections clcs11 = new ClsLedgerCommonSelections("M089", "Purchase Received Note", "credit discount", "11", ledgerId, ledgerAccNum, "6", "4019148004", "S");
            ClsLedgerCommonSelections clcs12 = new ClsLedgerCommonSelections("M201", "GRN Return Note", "return goods to credit supplier", "11", ledgerId, ledgerAccNum, "9", "50130690001", "S");
            ClsLedgerCommonSelections clcs13 = new ClsLedgerCommonSelections("M201", "GRN Return Note", "return goods to cash supplier", "1", "1", "001", ledgerId, ledgerAccNum, "S");
            ClsLedgerCommonSelections clcs14 = new ClsLedgerCommonSelections("M229", "SUPPLIER OPENING BALANCE", "new opening balance of a supplier", "1", "4", "004", ledgerId, ledgerAccNum, "S");

            ClsLedgerCommonSelections[] CommonAccountArray = new ClsLedgerCommonSelections[14];
            CommonAccountArray[0] = clcs1;
            CommonAccountArray[1] = clcs2;
            CommonAccountArray[2] = clcs3;
            CommonAccountArray[3] = clcs4;
            CommonAccountArray[4] = clcs5;
            CommonAccountArray[5] = clcs6;
            CommonAccountArray[6] = clcs7;
            CommonAccountArray[7] = clcs8;
            CommonAccountArray[8] = clcs9;
            CommonAccountArray[9] = clcs10;
            CommonAccountArray[10] = clcs11;
            CommonAccountArray[11] = clcs12;
            CommonAccountArray[12] = clcs13;
            CommonAccountArray[13] = clcs14;

            for (ClsLedgerCommonSelections CommonAccountArray1 : CommonAccountArray) {

                LedgerCommonSelectionsRequest ledgerCommonSelections = new LedgerCommonSelectionsRequest(
                        CommonAccountArray1.getCredit_acc_id_STRING() == null ?
                                0 : Integer.parseInt(CommonAccountArray1.getCredit_acc_id_STRING()),
                        CommonAccountArray1.getCredit_acc_num_STRING() == null ?
                                "0" : CommonAccountArray1.getCredit_acc_num_STRING(),
                        CommonAccountArray1.getDebit_acc_id_STRING() == null ?
                                0 : Integer.parseInt(CommonAccountArray1.getDebit_acc_id_STRING()),
                        CommonAccountArray1.getDebit_acc_num_STRING() == null ?
                                "0" : CommonAccountArray1.getDebit_acc_num_STRING(),
                        CommonAccountArray1.getDescription_STRING(),
                        CommonAccountArray1.getFram_name_STRING(),
                        CommonAccountArray1.getFrame_id_STRING(),
                        CommonAccountArray1.getPay_mode_STRING(),
                        CommonAccountArray1.getRecord_right_STRING()
                );
                selectionsRequestList.add(ledgerCommonSelections);
            }
        }


        return  selectionsRequestList;
    }

}
