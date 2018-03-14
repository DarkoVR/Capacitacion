package com.darkovr.patm.Api.Models;

/**
 * Created by marco on 5/03/18.
 */

public class Empleado {
    int cveemp,cvepuesto,cveusr;
    String nomemp,apepat,apemat,fechanac,emailemp;

    public Empleado(int cveemp, int cvepuesto, int cveusr, String nomemp, String apepat, String apemat, String fechanac, String emailemp) {
        this.cveemp = cveemp;
        this.cvepuesto = cvepuesto;
        this.cveusr = cveusr;
        this.nomemp = nomemp;
        this.apepat = apepat;
        this.apemat = apemat;
        this.fechanac = fechanac;
        this.emailemp = emailemp;
    }

    public int getCveemp() {
        return cveemp;
    }

    public void setCveemp(int cveemp) {
        this.cveemp = cveemp;
    }

    public int getCvepuesto() {
        return cvepuesto;
    }

    public void setCvepuesto(int cvepuesto) {
        this.cvepuesto = cvepuesto;
    }

    public int getCveusr() {
        return cveusr;
    }

    public void setCveusr(int cveusr) {
        this.cveusr = cveusr;
    }

    public String getNomemp() {
        return nomemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public String getApepat() {
        return apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    public String getApemat() {
        return apemat;
    }

    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getEmailemp() {
        return emailemp;
    }

    public void setEmailemp(String emailemp) {
        this.emailemp = emailemp;
    }
}
