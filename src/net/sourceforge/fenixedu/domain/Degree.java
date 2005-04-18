/*
 * Degree.java
 *
 * Created on 31 de Outubro de 2002, 15:19
 */

package net.sourceforge.fenixedu.domain;

import net.sourceforge.fenixedu.util.TipoCurso;

/**
 * 
 * @author Nuno Nunes & Joana Mota
 */

public class Degree extends Degree_Base {
    protected TipoCurso tipoCurso;

    /** Construtor sem argumentos p�blico requerido pela moldura de objectos OJB */
    public Degree() {
    }

    public Degree(Integer idInternal) {
        setIdInternal(idInternal);
    }

    public Degree(String sigla, String nome, TipoCurso tipoCurso) {
        setSigla(sigla);
        setNome(nome);
        setTipoCurso(tipoCurso);
    }

    public boolean equals(Object obj) {
        boolean resultado = false;
        if (obj instanceof IDegree) {
            IDegree curso = (IDegree) obj;
            resultado = getSigla().equals(curso.getSigla());
        }
        return resultado;
    }

    public IDegreeCurricularPlan getNewDegreeCurricularPlan() {
        IDegreeCurricularPlan degreeCurricularPlan = null;

        try {
            Class classDefinition = Class.forName(getConcreteClassForDegreeCurricularPlans());
            degreeCurricularPlan = (IDegreeCurricularPlan) classDefinition.newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (ClassNotFoundException e) {
        }

        return degreeCurricularPlan;
    }

    public String toString() {
        String result = "[CURSO";
        result += ", codInt=" + getIdInternal();
        result += ", sigla=" + getSigla();
        result += ", nome=" + getNome();
        result += ", tipoCurso=" + getTipoCurso();
        result += "]";
        return result;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }
}