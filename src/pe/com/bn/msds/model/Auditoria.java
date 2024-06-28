package pe.com.bn.msds.model;

import java.util.Date;



public class Auditoria {
    private Long id;
    private Date fecha;
    private String fecha1;
    private String hora;
    private String usuario;
    private String accion;
    private String aplicacion;
    private String dato1;
    private String dato2;
    private String dato3;

    public Auditoria() {
		super();
	}
    

	public String getFecha1() {
		return fecha1;
	}


	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}


	// Constructor
    public Auditoria(Long id, Date fecha, String fecha1, String hora, String usuario, String accion, String aplicacion,
                     String dato1, String dato2, String dato3) {
        this.id = id;
        this.fecha = fecha;
        this.fecha1 = fecha1;
        this.hora = hora;
        this.usuario = usuario;
        this.accion = accion;
        this.aplicacion = aplicacion;
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.dato3 = dato3;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    public String getDato3() {
        return dato3;
    }

    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }
}
