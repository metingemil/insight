package javaLearn.interfaces;

public class Person extends CustomObject{
	private String mNume;
	private String mPrenume;

	public Person(int id, String nume, String prenume) {
		super(id);
		mNume = nume;
		mPrenume = prenume;
	}

	public String getNume() {
		return mNume;
	}

	public String getPrenume() {
		return mPrenume;
	}

	@Override
	public String toString() {
		return String.format("Peson[Id=%d, Nume=%s. Prenume=%s]", getId(), mNume, mPrenume);
	}

	/*
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;

		Person that = (Person) obj;
		if (that.getId() != this.getId())
			return false;
		if (that.mNume != this.mNume)
			return false;
		if (that.mPrenume != this.mPrenume)
			return false;

		return true;
	}
	*/
}
