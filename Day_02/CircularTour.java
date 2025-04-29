class CircularTour {
    static class PetrolPump {
        int petrol, distance;
        PetrolPump(int p, int d) {
            petrol = p;
            distance = d;
        }
    }

    int findStartPoint(PetrolPump[] pumps) {
        int start = 0, currPetrol = 0, prevPetrol = 0;
        for (int i = 0; i < pumps.length; i++) {
            currPetrol += pumps[i].petrol - pumps[i].distance;
            if (currPetrol < 0) {
                start = i + 1;
                prevPetrol += currPetrol;
                currPetrol = 0;
            }
        }
        return (currPetrol + prevPetrol >= 0) ? start : -1;
    }

    public static void main(String[] args) {
        CircularTour ct = new CircularTour();
        PetrolPump[] pumps = {
            new PetrolPump(4, 6),
            new PetrolPump(6, 5),
            new PetrolPump(7, 3),
            new PetrolPump(4, 5),
        };
        int start = ct.findStartPoint(pumps);
        if (start == -1) {
            System.out.println("No solution");
        } else {
            System.out.println("Start at pump " + start);
        }
    }
}

