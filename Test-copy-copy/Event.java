import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    private String date;
    private String venue;
    private int seatingCapacity;
    private int availableSeats;
    private boolean[][] seatingChart;

    public Event(String name, String date, String venue, int seatingCapacity) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.seatingCapacity = seatingCapacity;
        this.availableSeats = seatingCapacity;
        this.seatingChart = new boolean[10][10]; 
        initializeSeatingChart();
    }

    private void initializeSeatingChart() {
        for (int i = 0; i < seatingChart.length; i++) {
            for (int j = 0; j < seatingChart[i].length; j++) {
                seatingChart[i][j] = true; // All seats are initially available
            }
        }
    }

    public static List<Event> getUpcomingEvents() {
        List<Event> upcomingEvents = new ArrayList<>();
        
        upcomingEvents.add(new Event("Concert", "2024-02-10", "Bucks Centre", 100));
        upcomingEvents.add(new Event("Theater Play", "2024-02-15", "Bucks Centre", 150));
        upcomingEvents.add(new Event("Comedy Show", "2024-02-20", "Bucks Centre", 120));
        return upcomingEvents;
    }

    public void displaySeatingChart() {
        
        System.out.print("   ");
        for (int i = 1; i <= seatingChart[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        
        for (int i = 0; i < seatingChart.length; i++) {
            System.out.print((i + 1) + "  "); // Row numbers
            for (int j = 0; j < seatingChart[i].length; j++) {
                if (seatingChart[i][j]) {
                    System.out.print("O "); // Available seat
                } else {
                    System.out.print("X "); // Reserved seat
                }
            }
            System.out.println(); 
        }
    }

    public boolean reserveSeat(int row, int column) {
        if (row >= 0 && row < seatingChart.length && column >= 0 && column < seatingChart[0].length) {
            if (seatingChart[row][column]) {
                seatingChart[row][column] = false; 
                availableSeats--;
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}