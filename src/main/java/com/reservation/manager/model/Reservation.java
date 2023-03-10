package com.reservation.manager.model;

import com.reservation.manager.Enum.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reservations")
@SQLDelete(sql = "UPDATE reservations SET deleted = true WHERE id=?")
public class Reservation  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER, optional = false )
    @JoinColumn(name = "user_id", updatable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "unit_id", updatable = false)
    @JsonIgnore
    private RentalUnit unit;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @NotNull(message = "Amount of people is required")
    @Column(name = "amount_of_people")
    @Min(1)
    @Max(9)
    private int amountOfPeople;

    @NotNull(message = "Check-in date is required (YYYY-MM-dd).")
    @Column(name = "check_in")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent
    private LocalDate checkIn;

    @NotNull(message = "Check-out date is required (YYYY-MM-dd).")
    @Column(name = "check_out")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    private LocalDate checkOut;

    @NotNull(message = "Cost per night is required")
    @Column(name = "cost_per_night")
    @Min(0)
    private Double costPerNight;

    @NotNull(message = "Partial payment is required")
    @Column(name = "partial_payment")
    @Min(0)
    private Double partialPayment;

    @NotNull(message = "Percent is required")
    @Min(0)
    @Max(value = 100, message = "percent max value is 100")
    private int percent;

    @NotNull(message = "Debit amount is required")
    @Min(value = 0,message = "Invalid amount")
    private Double debit;

    @NotNull(message = "Total amount is required")
    @Min(value = 0,message = "Invalid amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Override
    public String toString() {
        return "RESERVATION ID: " + this.id + "\n" +
                "Unit name: " + this.getUnit().getName() + "\n" +
                "Amount of people: " + this.amountOfPeople + "\n" +
                "Check-in: " + this.checkIn + "\n" +
                "check-out: " + this.checkOut + "\n" + "\n"+

                "USER INFORMATION: " + "\n" +
                "Name: " + this.getUser().getFirstName() + " " + this.getUser().getLastName() + "\n" +
                "Phone: " + this.getUser().getAreaCode() + this.getUser().getPhoneNumber() + "\n" + "\n" +

                "COST DETAIL: " + "\n" +
                "Cost per night: " + this.costPerNight + "\n" +
                "Payment percent: %" + this.percent + "\n" +
                "Total: " + this.totalAmount + "\n\n" +

                "PAYED: " + this.partialPayment + "\n" +
                "DEBIT: " + this.debit + "\n\n";
    }
}
