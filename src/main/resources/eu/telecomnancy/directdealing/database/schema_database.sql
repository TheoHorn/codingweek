CREATE TABLE ACCOUNT (
    mail TEXT NOT NULL PRIMARY KEY,
    lastname TEXT NOT NULL,
    firstname TEXT NOT NULL,
    password TEXT NOT NULL,
    balance REAL,
    type INTEGER NOT NULL,
    localisation TEXT,
    sleep INTEGER NOT NULL
);

CREATE TABLE CONTENT (
    idContent INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT,
    category TEXT NOT NULL,
    image BLOB,
    price REAL,
    isEquipment INTEGER NOT NULL
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
    startTime DATETIME NOT NULL,
    endTime DATETIME,
    recurring INTEGER,
    idOffer INTEGER NOT NULL,
    FOREIGN KEY (idOffer) REFERENCES OFFER(idOffer)
);

CREATE TABLE RESERVATION (
    mail TEXT NOT NULL,
    idSlot INTEGER NOT NULL,
    dateReservation DATETIME NOT NULL,
    PRIMARY KEY (mail, idSlot),
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

