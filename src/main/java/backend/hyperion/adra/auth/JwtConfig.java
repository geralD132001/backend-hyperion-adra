package backend.hyperion.adra.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA="alguna.clave.secreta.12345678";

	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAqgyM/3rzb5NO/b+SSp/+yKMeBxGgo04zhQIEp8TNZHjHr0SS\r\n"
			+ "/Ex+yzVDQEN/SgcHlld0evcAu9jWDM9wS9MYInfJVdgioBQJ4D7YFiw7QDZ41mtL\r\n"
			+ "joE6E0fQf1C59WaxR/PHe6RMYTnlePDFKJmO/JUiR4HM0ojX0weHJZUtvaigCim6\r\n"
			+ "QeMhO5HC3e52E91SAoTJu7GTCXlNfw4zpAVC11breT3FgoMSte5U6g7QSgl4Q5S4\r\n"
			+ "ffNbXxN4NQutgwzw1KvtYcy30L/PfClzzrywViBSpBfJSNH3vI105uhJ8ljyDV9Z\r\n"
			+ "bo1j8CoLE+KQQ4H4psyOKuTyeYQ+s6YRZo4wIwIDAQABAoIBAARGrIUAIn77xVC8\r\n"
			+ "MIGP5GhHMcobt15j6Qe/NT7CxCdyohLWNpwBkG6ruDU1fU3vP1GYgWVWML6IhVlt\r\n"
			+ "HfXKKSE0wetWtAgyeM0ax3RHSHYk6IT7zbqXpU420BdjgEmbe3nDt5B0YTdQrWSN\r\n"
			+ "KWgL9HyFg4cozG2+4y57Ufqv9aZ/PIixe1lCyAu9wMl1azYsBK3XeRVQccN4DO1A\r\n"
			+ "T0n/F5uF9JD3+GwZx2o44dXDSaW29/4qpJlguG2NXkX3SKpgtrYgise4J0rPRIZR\r\n"
			+ "5ohmPUQkJvIF9W1TKdYdiEBNbGslV2Ld1ecBiGevxo9LoHxDAD++HMQMVu9Ov25l\r\n"
			+ "gTov1YECgYEA0ZW2hVNHxVLvOSNivcJ8luKpPKiXlpcOXo+wXLWlFwS97bn3JJ0o\r\n"
			+ "5jkAnCZoqG+ykHNNaHtOzA/ZEKvpAR4StDP2ox7HqVDVnH7TKV6RbPDX44y1fw3B\r\n"
			+ "Zuw/DIE74TA7kgGrq+P8q06wXi5cRog+x18uFv5RU0ym3sv2ng3Ypc8CgYEAz7Vh\r\n"
			+ "LkrbxwMS7AahXQqpm8R+g6HG3gOIsnYCanTXORYVP8dFK3KuIey6I9k441kARXZk\r\n"
			+ "tUnk5pFvP0AxzHJu4hbh4xNXOsb5NxetxRwqYhgDmt6zJuyuhW46xy4cw2NlDzyZ\r\n"
			+ "dhwNFaUs5jS4B+J85kpb5q7j72KpYFk/qhLzuW0CgYActHSbLhzWWdWJ7d7IPqrx\r\n"
			+ "v3mOZUjdxzKgoO12xSonTO72cFH5Ic1IOuZqfJg4+ZNrVV90v+665DDGp8Tx8jx9\r\n"
			+ "keBl1wFhn0CifnxNXjZsxU/MSqlRDYE/p3u7KrgYz5JZHQF2OXORwuVFAPSCdIak\r\n"
			+ "p9SYMZzR292/BhBw9VSG4QKBgC9kGgU+7UBOEj4qER0ydppkzdzox4AWXFUjmc+C\r\n"
			+ "UyziPfzsUXrlUpfI9HuDb3hn8SjoJ3g5oPF+k1xiIDqejXAmemmeGxP9HBWW4H4N\r\n"
			+ "vBnfksr+LvAdpoApM14IxmVKQ1+jmvwY0F907Xak5qxd39OHecp7cuRGpZCnLGB4\r\n"
			+ "rIVNAoGBAM7ufl3zIla3+BQ+UNhJ0fGsL9cP1XLoeEz/KwQennV+sUiWBsQIUIda\r\n"
			+ "7Cq295/Kj9zIMPN5hrMNyXQmr3ul03tHyIxWQ1O+OUbAAlleyzgttLLghQZj3vv0\r\n"
			+ "bEUxds8cVEagIH6jqZRLYt1h2Uq04LmtXHlaf9erJ410KlbCXPoa\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqgyM/3rzb5NO/b+SSp/+\r\n"
			+ "yKMeBxGgo04zhQIEp8TNZHjHr0SS/Ex+yzVDQEN/SgcHlld0evcAu9jWDM9wS9MY\r\n"
			+ "InfJVdgioBQJ4D7YFiw7QDZ41mtLjoE6E0fQf1C59WaxR/PHe6RMYTnlePDFKJmO\r\n"
			+ "/JUiR4HM0ojX0weHJZUtvaigCim6QeMhO5HC3e52E91SAoTJu7GTCXlNfw4zpAVC\r\n"
			+ "11breT3FgoMSte5U6g7QSgl4Q5S4ffNbXxN4NQutgwzw1KvtYcy30L/PfClzzryw\r\n"
			+ "ViBSpBfJSNH3vI105uhJ8ljyDV9Zbo1j8CoLE+KQQ4H4psyOKuTyeYQ+s6YRZo4w\r\n"
			+ "IwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
