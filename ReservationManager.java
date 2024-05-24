package RestaurantPractice;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReservationManager {

    private List<Reservation> reservations;

    public ReservationManager() {
        this.reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void viewReservations() {
        System.out.println("Reservation: ");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getReserationInfo());
        }

    }

    public boolean cancelReservation(int reservationId) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getReservationId() == reservationId) {
                iterator.remove(); 
                return true;
            }
        }
        return false;
    }

    public boolean isTimeSlotAvailable(Date reservationDate, String reservationTime) {
        for (Reservation reservation : reservations) {
            if (reservation.getResevationDate().equals(reservationDate) &&
                    reservation.getReservationTime().equals(reservationTime)) {
                return false;

            }
        }
        return true;
    }
}
