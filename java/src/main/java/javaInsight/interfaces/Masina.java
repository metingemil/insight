package javaInsight.interfaces;

public class Masina extends CustomObject {
	private String mMarca;
	private String mModel;

	public Masina(int id, String marca, String model) {
		super(id);
		mMarca = marca;
		mModel = model;
	}

	public String getMarca() {
		return mMarca;
	}

	public String getModel() {
		return mModel;
	}

	@Override
	public String toString() {
		return String.format("Peson[Id=%d, Marca=%s. Model=%s]", getId(), mMarca, mModel);
	}

	/*
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;

		Masina that = (Masina) obj;
		if (that.getId() != this.getId())
			return false;
		if (that.mMarca != this.mMarca)
			return false;
		if (that.mModel != this.mModel)
			return false;

		return true;
	}
	*/
}
