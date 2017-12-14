package org.springboot.esbcollecthelper.service.esbMsgCount.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springboot.esbcollecthelper.service.cache.TransErrorListCache;
import org.springboot.esbcollecthelper.service.esbMsgCount.TransErrorHandler;
import org.springboot.esbcollecthelper.util.common.DateHelper;
import org.springboot.esbcollecthelper.util.common.ListHelper;
import org.springboot.esbcollecthelper.util.excel.ExcelWriterForExitence;
import org.springboot.esbcollecthelper.util.file.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransErrorHandlerImpl implements TransErrorHandler{
	Logger logger = Logger.getLogger(getClass());
	
	private String sourceFilePath = "../excelTemplate/ValueTooLarge.xlsx";
	private String targetFilePath = "../excelTemplate/handleRecord/";
	private String heads = "SQL,ERROR_REASON,FILENAME_PATTERN,PARSE_RULE,PATH_TIME_STAMP,RECEIVE_TIME_STAMP,SOURCE_PATH,TABLE_NAME";
	private String sourceOtherFilePath = "../excelTemplate/OtherError.xlsx";
	private String otherHeads = "ERROR_REASON,FILENAME_PATTERN,PARSE_RULE,PATH_TIME_STAMP,RECEIVE_TIME_STAMP,SOURCE_PATH,TABLE_NAME";
	
	@Autowired
	ExcelWriterForExitence excelWriterForExitence;
	
	String targetFileName;

	@Override
	public byte[] handle() throws IOException {
		DateHelper.setSimpleFormat("yyyyMMddHHmmss");
		targetFileName = String.format("ValueTooLarge_%s.xlsx", DateHelper.getDayString(0));
		String vtargetFilePath = targetFilePath+targetFileName;
	    FileHelper.FileCopy(sourceFilePath, vtargetFilePath);
		excelWriterForExitence.setHeads(heads.split(","));
		excelWriterForExitence.setList(ListHelper.ObjectToString(TransErrorListCache.vtlList));
		excelWriterForExitence.dealExcel(vtargetFilePath);
		TransErrorListCache.initVtlList();
		return FileHelper.FileByte(vtargetFilePath);
	}

	@Override
	public String getTargetFileName() {
		return targetFileName;
	}

	@Override
	public Map<String, Integer> errorLeftCount() {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("INLIST", TransErrorListCache.inList.size());
		map.put("VTLLIST", TransErrorListCache.vtlList.size());
		map.put("FLFLIST", TransErrorListCache.flfList.size());
		map.put("OELIST", TransErrorListCache.oeList.size());
		return map;
	}

	@Override
	public byte[] handleOther() throws IOException {
		DateHelper.setSimpleFormat("yyyyMMddHHmmss");
		targetFileName = String.format("OtherError_%s.xlsx", DateHelper.getDayString(0));
		String otargetFilePath = targetFilePath+targetFileName;
	    FileHelper.FileCopy(sourceOtherFilePath, otargetFilePath);
		excelWriterForExitence.setHeads(otherHeads.split(","));
		excelWriterForExitence.setList(ListHelper.ObjectToString(TransErrorListCache.oeList));
		excelWriterForExitence.dealExcel(otargetFilePath);
		TransErrorListCache.initOeList();
		return FileHelper.FileByte(otargetFilePath);
	}

}
