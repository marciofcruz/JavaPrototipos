package br.com.unip.aps.ws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import br.com.unip.aps.ws.adapter.DateTimeAdapter;

@XmlRootElement(name = "contato")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table
public class Contato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5272202378740191163L;

	@XmlElement(name = "id", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contato_id", nullable = false, insertable = true, updatable = false, unique = true)
	private Long id;

	@XmlElement(name = "telefone", required = true)
	@NotEmpty
	@Pattern(regexp = "^$|[(]\\d{2}[)]\\s\\d{4}[-]\\d{4}", message = "Formato inválido!")
	@Size(min = 14, max = 14, message = "*O tamanho requerido é 14.")
	@Column(length = 14, nullable = false, insertable = true, updatable = false, unique = false)
	private String telefone;

	@XmlElement(name = "celular", required = true)
	@NotEmpty
	@Pattern(regexp = "^$|[(]\\d{2}[)]\\s\\d{4}[-]\\d{4,5}", message = "Formato inválido!")
	@Size(min = 14, max = 15, message = "*O tamanho requerido é de 14 ao 15.")
	@Column(length = 15, nullable = false, insertable = true, updatable = false, unique = false)
	private String celular;

	@XmlElement(name = "email", required = true)
	@NotEmpty
	@Email(message = "Formato inválido!")
	@Size(min = 5, max = 120, message = "*O tamanho requerido é de 5 ao 120.")
	@Column(length = 120, nullable = false, insertable = true, updatable = false, unique = false)
	private String email;

	@XmlElement(name = "dataCriacao", required = true)
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(nullable = false, insertable = true, updatable = false, unique = false)
	private DateTime dataCriacao;

	@XmlElement(name = "dataModificacao", required = true)
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(nullable = true, insertable = false, updatable = true, unique = false)
	private DateTime dataModificacao;

	@XmlTransient
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ocorrencia_id", nullable = false, insertable = true, updatable = false, unique = false)
	private Ocorrencia ocorrencia = new Ocorrencia();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(DateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public DateTime getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(DateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result
				+ ((dataModificacao == null) ? 0 : dataModificacao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (dataModificacao == null) {
			if (other.dataModificacao != null)
				return false;
		} else if (!dataModificacao.equals(other.dataModificacao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@PrePersist
	public void prePersist() {
		dataCriacao = DateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		dataModificacao = DateTime.now();
	}

}
