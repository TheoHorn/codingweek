package eu.telecomnancy.directdealing.views.offer;

import com.calendarfx.model.*;
import com.calendarfx.view.*;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReservationPopupController {
    @FXML
    private BorderPane root;
    private Application app;
    private CalendarSource myCalendarSource2;

    public ReservationPopupController() {
        this.app = Application.getInstance();
    }

    @FXML
    public void initialize() throws SQLException {
        CalendarView calendarView = new CalendarView();

        // Configure the calendar view
        calendarView.getCalendarSources().clear();
        calendarView.showWeekPage();
        calendarView.showAddCalendarButtonProperty().setValue(false);

        CalendarSource myCalendarSource = new CalendarSource("Créneaux");
        myCalendarSource2 = new CalendarSource("Réservé");
        Calendar slots = new Calendar("Créneaux");
        Calendar booked = new Calendar("Réservé");
        Calendar requested = new Calendar("Demandé");
        Calendar toAdd = new Calendar("Ajouter");
        slots.setStyle(Calendar.Style.STYLE1);
        booked.setStyle(Calendar.Style.STYLE2);
        requested.setStyle(Calendar.Style.STYLE3);
        toAdd.setStyle(Calendar.Style.STYLE4);
        slots.readOnlyProperty().setValue(true);
        requested.readOnlyProperty().setValue(true);
        booked.readOnlyProperty().setValue(true);

        try {
            Interval interval;
            LocalDateTime start;
            LocalDateTime end;
            Entry<?> entry;
            LocalDateTime tmp;
            for (Slot slot: app.getSlotDAO().get(app.getLastOffer().getIdOffer())) {
                tmp = slot.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                if (slot.getRecurrence() != 0) {
                    while (tmp.isBefore(slot.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())) {
                        interval = new Interval(tmp, tmp);
                        if (app.getReservationDAO().get(slot.getId()) != null) {
                            booked.addEntry(new Entry<>("Réservé", interval));
                        } else if (app.getDemandeDAO().get("idSlot", slot.getId()) != null) {
                            requested.addEntry(new Entry<>("Demandé", interval));
                        } else {
                            entry = new Entry<>("Disponible", interval);
                            entry.fullDayProperty().setValue(true);
                            slots.addEntry(entry);
                        }
                        tmp = tmp.plusDays(slot.getRecurrence());
                    }
                } else {
                    start = slot.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    end =  slot.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    interval = new Interval(start, end);
                    if (app.getReservationDAO().get(slot.getId()) != null) {
                        booked.addEntry(new Entry<>("Réservé", interval));
                    } else if (app.getDemandeDAO().get("idSlot", slot.getId()) != null) {
                        requested.addEntry(new Entry<>("Demandé", interval));
                    } else {
                        entry = new Entry<>("Disponible", interval);
                        entry.fullDayProperty().setValue(true);
                        slots.addEntry(entry);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        myCalendarSource.getCalendars().addAll(slots, requested, booked);
        myCalendarSource2.getCalendars().addAll(toAdd);
        calendarView.getCalendarSources().addAll(myCalendarSource, myCalendarSource2); // (5)
        calendarView.setRequestedTime(LocalTime.now());
//        calendarView.setEntryDetailsCallback(this::addEntryToWeekPage);

        root.setCenter(calendarView);
    }

    @FXML
    private void save() throws Exception {
        Map<LocalDate, List<Entry<?>>> addedEntries = myCalendarSource2.getCalendars().get(0).findEntries(LocalDate.now().minusYears(2), LocalDate.now().plusYears(2), ZoneId.systemDefault());
        List<Slot> slots = new ArrayList<>();
        for (LocalDate key: addedEntries.keySet()) {
            for (Entry<?> entry: addedEntries.get(key)) {
                LocalDateTime start = entry.getInterval().getStartDateTime();
                LocalDateTime end = entry.getInterval().getEndDateTime();
                Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
                Date endDate = Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
                try {
                    slots.add(new Slot(startDate, endDate, 0, app.getLastOffer().getIdOffer()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        app.validateNewDemand(app.getLastOffer(), slots);
    }
}
