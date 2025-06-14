
class City {
    int ref;
    String nom, gen, acc, dat, enName, region, department;

    public City(String line) {
        String[] parts = line.split("#");
        this.ref = Integer.parseInt(parts[0]);
        this.nom = parts[1];
        this.gen = parts[2];
        this.acc = parts[3];
        this.dat = parts[4];
        this.enName = parts[5];
        this.region = parts[6];
        this.department = parts[7];
    }
}