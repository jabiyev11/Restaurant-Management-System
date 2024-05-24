package RestaurantPractice;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Reservation {

    private static int idCounter = 1;
    private int reservationId;
    private String customerName;
    private Date resevationDate;
    private String reservationTime;
    private int numberOfPeople;

    public Reservation(String customerName, Date resevationDate, String reservationTime, int numberOfPeople) {
        this.reservationId = idCounter++;
        this.customerName = customerName;
        this.resevationDate = resevationDate;
        this.reservationTime = reservationTime;
        this.numberOfPeople = numberOfPeople;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Date getResevationDate() {
        return resevationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public String getReserationInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("ID: %d, Name: %s, Date: %s, Time: %s, People: %d", reservationId, customerName,
                dateFormat.format(resevationDate), reservationTime, numberOfPeople);

    }

}
