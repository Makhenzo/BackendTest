package com.multichoice.test.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;




@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Customers")
public class Customer{


    @Id
    private int id;

    private String firstName;
    private String lastName;
    private String gender;
    
   

    public Customer(int id, String firstName, String lastName, String gender) {
    		super();
    	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}