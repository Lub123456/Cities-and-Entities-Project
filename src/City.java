
class City {
    int ref;
    String gen, dat, enName, region, department;

    public City(String line) {
        String[] parts = line.split("#");
        this.ref = Integer.parseInt(parts[0]);
        this.gen = parts[1];
        this.dat = parts[2];
        this.enName = parts[3];
        this.region = parts[4];
        this.department = parts[5];
    }
}