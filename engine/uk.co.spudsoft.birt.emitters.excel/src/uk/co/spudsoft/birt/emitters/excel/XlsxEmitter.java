/*************************************************************************************
 * Copyright (c) 2011, 2012, 2013 James Talbut.
 *  jim-emitters@spudsoft.co.uk
 *  
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     James Talbut - Initial implementation.
 ************************************************************************************/

package uk.co.spudsoft.birt.emitters.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.birt.report.engine.content.IReportContent;

/**
 * XlsxEmitter is the leaf class for implementing the ExcelEmitter with XSSFWorkbook.
 * @author Jim Talbut
 *
 */
public class XlsxEmitter extends ExcelEmitter {
	
	/**
	 */
	public XlsxEmitter() {
		super(StyleManagerXUtils.getFactory());
		log.debug("Constructed XlsxEmitter");
	}

	public String getOutputFormat() {
		return "xlsx";
	}

	protected Workbook createWorkbook(final IReportContent report) {
		System.out.println(renderOptions.getOptions());
		System.out.println(renderOptions.getOption(SXSSF_WORKBOOK));
		System.out.println(report.getDesign().getUserProperties());
		if( EmitterServices.booleanOption( renderOptions, report, SXSSF_WORKBOOK, false ) )
		{
			log.debug("XlsxEmitter - creating workbook using SXSSFWorkbook");
			System.out.println("XlsxEmitter - creating workbook using SXSSFWorkbook");
			return new SXSSFWorkbook(10);
		}
		return new XSSFWorkbook();
	}
	
	protected Workbook openWorkbook( File templateFile ) throws IOException {
		InputStream stream = new FileInputStream( templateFile );
		try {
			return new XSSFWorkbook( stream );
		} finally {
			stream.close();
		}
	}

}
