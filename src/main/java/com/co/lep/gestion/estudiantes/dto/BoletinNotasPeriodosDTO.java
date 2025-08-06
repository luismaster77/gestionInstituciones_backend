package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BoletinNotasPeriodosDTO implements Serializable{

	private static final long serialVersionUID = 6008444664684721529L;
	
	private String nomMateria;
    private String docente;
    private BigDecimal notaPrimerPeriodo;
    private BigDecimal notaSegundoPeriodo;
    private BigDecimal notaTercerPeriodo;
    private BigDecimal notaCuartoPeriodo;
    private Integer intensidadHoras;
    private String escalaNacional;
    private String eiFinal;

    // Constructor
    public BoletinNotasPeriodosDTO(String nomMateria, String docente, 
                                   BigDecimal notaPrimerPeriodo, BigDecimal notaSegundoPeriodo, 
                                   BigDecimal notaTercerPeriodo, BigDecimal notaCuartoPeriodo, 
                                   Integer intesidadHoraria, String escalaNacional) {
        this.nomMateria = nomMateria;
        this.docente = docente;
        this.notaPrimerPeriodo = notaPrimerPeriodo;
        this.notaSegundoPeriodo = notaSegundoPeriodo;
        this.notaTercerPeriodo = notaTercerPeriodo;
        this.notaCuartoPeriodo = notaCuartoPeriodo;
        this.intensidadHoras = intesidadHoraria;
        this.escalaNacional = escalaNacional;
    }

	public String getNomMateria() {
		return nomMateria;
	}

	public void setNomMateria(String nomMateria) {
		this.nomMateria = nomMateria;
	}

	public String getDocente() {
		return docente;
	}

	public void setDocente(String docente) {
		this.docente = docente;
	}

	public BigDecimal getNotaPrimerPeriodo() {
		return notaPrimerPeriodo;
	}

	public void setNotaPrimerPeriodo(BigDecimal notaPrimerPeriodo) {
		this.notaPrimerPeriodo = notaPrimerPeriodo;
	}

	public BigDecimal getNotaSegundoPeriodo() {
		return notaSegundoPeriodo;
	}

	public void setNotaSegundoPeriodo(BigDecimal notaSegundoPeriodo) {
		this.notaSegundoPeriodo = notaSegundoPeriodo;
	}

	public BigDecimal getNotaTercerPeriodo() {
		return notaTercerPeriodo;
	}

	public void setNotaTercerPeriodo(BigDecimal notaTercerPeriodo) {
		this.notaTercerPeriodo = notaTercerPeriodo;
	}

	public BigDecimal getNotaCuartoPeriodo() {
		return notaCuartoPeriodo;
	}

	public void setNotaCuartoPeriodo(BigDecimal notaCuartoPeriodo) {
		this.notaCuartoPeriodo = notaCuartoPeriodo;
	}
	
	public Integer getIntensidadHoras() {
		return intensidadHoras;
	}

	public void setIntensidadHoras(Integer intensidadHoras) {
		this.intensidadHoras = intensidadHoras;
	}

	public String getEscalaNacional() {
		return escalaNacional;
	}

	public void setEscalaNacional(String escalaNacional) {
		this.escalaNacional = escalaNacional;
	}

	public String getEiFinal() {
		return formatNota(new BigDecimal(eiFinal));
	}

	public void setEiFinal(String eiFinal) {
		this.eiFinal = eiFinal;
	}

	private String formatNota(BigDecimal nota) {
        if (nota == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(nota);
    }
	
	private String formatAtributo(Integer valor) {
        if (valor == null) {
            return "";
        }
        return String.valueOf(valor);
    }
	
	public String getNotaPrimerPeriodoFormatted() {
        return formatNota(notaPrimerPeriodo);
    }

    public String getNotaSegundoPeriodoFormatted() {
        return formatNota(notaSegundoPeriodo);
    }

    public String getNotaTercerPeriodoFormatted() {
        return formatNota(notaTercerPeriodo);
    }

    public String getNotaCuartoPeriodoFormatted() {
        return formatNota(notaCuartoPeriodo);
    }
    
    public String getIntensidadHorasFormatted() {
    	return formatAtributo(intensidadHoras);
    }
    
}