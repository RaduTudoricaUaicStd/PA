package lab4;
import java.util.*;
import java.util.stream.*;

class Main{

	public static void main(String[] args) {
		var residentsArray = IntStream.rangeClosed(0, 3).mapToObj(
								i -> new Resident("R" + i)
							).toArray(Resident[]::new);

		var hospitalsArray = IntStream.rangeClosed(0, 2).mapToObj(
								i -> new Hospital("H"+i)
							).toArray(Hospital[]::new);

		List<Resident> listOfResidents = new ArrayList();
		listOfResidents.addAll(Arrays.asList(residentsArray));
		Collections.sort(listOfResidents, Comparator.comparing(Resident::getName));

		Set<Hospital> setOfHospitals = new TreeSet();
		setOfHospitals.addAll(Arrays.asList(hospitalsArray));

		Map<Resident, List<Hospital>> residentPref = new HashMap();
		residentPref.put(residentsArray[0], Arrays.asList(hospitalsArray[0], hospitalsArray[1], hospitalsArray[2]));
		residentPref.put(residentsArray[1], Arrays.asList(hospitalsArray[0], hospitalsArray[1], hospitalsArray[2]));
		residentPref.put(residentsArray[2], Arrays.asList(hospitalsArray[0], hospitalsArray[1]));
		residentPref.put(residentsArray[3], Arrays.asList(hospitalsArray[0], hospitalsArray[2]));
	
		Map<Hospital, List<Resident>> hospitalsPref = new TreeMap();
		hospitalsPref.put(hospitalsArray[0], Arrays.asList(residentsArray[3], residentsArray[0], residentsArray[1], residentsArray[2]));
		hospitalsPref.put(hospitalsArray[1], Arrays.asList(residentsArray[0], residentsArray[2], residentsArray[1]));
		hospitalsPref.put(hospitalsArray[2], Arrays.asList(residentsArray[0], residentsArray[1], residentsArray[3]));

		for(Map.Entry<Resident, List<Hospital>> iter : residentPref.entrySet()){
			System.out.print(iter.getKey());
			System.out.print(": ");
			System.out.println(iter.getValue());
		}

		System.out.println();

		for(Map.Entry<Hospital, List<Resident>> iter : hospitalsPref.entrySet()){
			System.out.print(iter.getKey());
			System.out.print(": ");
			System.out.println(iter.getValue());
		}
	}
}