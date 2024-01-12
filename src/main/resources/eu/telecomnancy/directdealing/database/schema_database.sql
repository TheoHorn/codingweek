CREATE TABLE ACCOUNT (
    mail TEXT NOT NULL PRIMARY KEY,
    lastname TEXT NOT NULL,
    firstname TEXT NOT NULL,
    password TEXT NOT NULL,
    balance REAL,
    type INTEGER NOT NULL,
    city TEXT,
    address TEXT,
    sleep INTEGER NOT NULL
);

CREATE TABLE CONTENT (
    idContent INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT,
    category TEXT NOT NULL,
    image BLOB,
    price REAL,
    isEquipment INTEGER NOT NULL,
    localisation TEXT
);

CREATE TABLE OFFER (
    idOffer INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    mail TEXT NOT NULL,
    isRequest INTEGER NOT NULL,
    idContent INTEGER NOT NULL,
    FOREIGN KEY (mail) REFERENCES ACCOUNT(mail),
    FOREIGN KEY (idContent) REFERENCES CONTENT(idContent)
);

CREATE TABLE SLOT (
    idSlot INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    startTime DATETIME,
    endTime DATETIME,
    recurring INTEGER,
    idOffer INTEGER NOT NULL,
    FOREIGN KEY (idOffer) REFERENCES OFFER(idOffer)
);

CREATE TABLE RESERVATION (
    mail TEXT NOT NULL,
    idSlot INTEGER NOT NULL PRIMARY KEY,
    dateReservation DATETIME NOT NULL,
    FOREIGN KEY (mail) REFERENCES ACCOUNT(mail),
    FOREIGN KEY (idSlot) REFERENCES SLOT(idSlot)
);

CREATE TABLE DEMANDE (
    idDemande INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    mail TEXT NOT NULL,
    idSlot INTEGER NOT NULL,
    dateDemande DATETIME NOT NULL,
    status INTEGER NOT NULL,
    FOREIGN KEY (mail) REFERENCES ACCOUNT(mail),
    FOREIGN KEY (idSlot) REFERENCES SLOT(idSlot)
);

CREATE TABLE EVALUATION (
    mailEvaluator TEXT NOT NULL,
    mailEvaluated TEXT NOT NULL,
    note INTEGER NOT NULL,
    PRIMARY KEY (mailEvaluator, mailEvaluated),
    FOREIGN KEY (mailEvaluator) REFERENCES ACCOUNT(mail),
    FOREIGN KEY (mailEvaluated) REFERENCES ACCOUNT(mail)
);


CREATE TABLE MESSAGING (
    idMessage INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    sender TEXT NOT NULL,
    receiver TEXT NOT NULL,
    content TEXT NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (sender) REFERENCES ACCOUNT(mail),
    FOREIGN KEY (receiver) REFERENCES ACCOUNT(mail)
);

CREATE TABLE DISPUTE (
    idDispute INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    idDemande INTEGER NOT NULL,
    content TEXT NOT NULL,
    attacker TEXT NOT NULL,
    defender TEXT NOT NULL,
    FOREIGN KEY (idDemande) REFERENCES DEMANDE(idDemande),
    FOREIGN KEY (attacker) REFERENCES ACCOUNT(mail),
    FOREIGN KEY (defender) REFERENCES ACCOUNT(mail)
);

INSERT INTO ACCOUNT VALUES ('test', 'test', 'test', '1000:5b42403161666264613731:e6a14d1676465d6581b15b2936642015ab884f4c0930605dfed7e08809b3f20f0e65021260b5d82aa0fe81cfb84fe0ffcf20c198b54cc3caf21d1bd471ee9ff1', 500, 1, 'Nancy', "227 rue jeanne d'arc", 0);
INSERT INTO ACCOUNT VALUES ('admin', 'admin', 'admin', '1000:5b42403433616161393762:004803c121408732b3a390dc8a3b1cb3540de63bebe92e996087154435a5a2bed9a77b0470bdc0df443210b54c244f454d79808da3e6ef2a78b32a4a41e0f9a4', 0, 2, '', '', 1);