
class NamedEntity {
    int cityRef;
    String nom, gen, acc, dat, gender, entityType;

    public NamedEntity(String line) {
        String[] parts = line.split("#");
        this.nom = parts[0];
        this.gen = parts[1];
        this.acc = parts[2];
        this.dat = parts[3];
        this.gender = parts[4];
        this.cityRef = Integer.parseInt(parts[5]);
        this.entityType = parts[6];
    }
}