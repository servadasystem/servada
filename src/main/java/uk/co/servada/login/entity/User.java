package uk.co.servada.login.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=3, max=50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min=3, max=50)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Size(min=3, max=50)
    @Column(name = "loginID", nullable = false)
    private String loginID;
    
/*    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "JOINING_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joiningDate;*/

/*    @NotNull
    @Digits(integer=8, fraction=2)
    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;*/


	@NotEmpty
    @Column(name = "password", unique=true, nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = firstName;
    }
    
    public String getLasttName() {
        return firstName;
    }

    public void setLastName(String name) {
        this.lastName = lastName;
    }
    

/*    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }*/


    public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }
  
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

/*    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", joiningDate="
                + joiningDate + ", salary=" + salary + ", username=" + username + "]";
    }*/

    @Override
    public String toString() {
        return "Employee [id=" + id + ", first_name=" + firstName + 
        		", last_name=" + lastName + ", password=" + password + "]";
    }
}
