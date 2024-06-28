package pe.com.bn.msds.reports.inte;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import pe.com.bn.msds.model.Programa;

public interface RepoXLS {
	
	public Workbook getReporteExcelProgramas( List<Programa> programas) ;
}
