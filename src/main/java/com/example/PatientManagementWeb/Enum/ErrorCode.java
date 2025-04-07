package com.example.PatientManagementWeb.Enum;

public enum ErrorCode {

    // Erreurs Techniques
    INVALID_UUID("Format UUID invalide"),
    DATABASE_ERROR("Une erreur est survenue lors de l'accès à la base de données"),

    // Erreurs d'authentification
    AUTHENTICATION_FAILED("Échec de l'authentification"),
    UNAUTHORIZED_ACCESS("Accès non autorisé"),
    SESSION_EXPIRED("La session a expiré"),

    // Erreurs Utilisateurs
    USER_NOT_FOUND("Utilisateur non trouvé"),
    USER_ALREADY_EXISTS("Cet utilisateur existe déjà"),
    INVALID_CREDENTIALS("Identifiants invalides"),
    PASSWORD_ENCODING_ERROR("Erreur lors du cryptage du mot de passe"),
    VALIDATION_ERROR("Le mot de passe ne peut pas etre vide"),

    // Erreurs Médecins
    MEDECIN_NOT_FOUND("Médecin non trouvé"),
    MEDECIN_ALREADY_EXISTS("medecin deja exist with the same id"),
    SPECIALITE_NOT_FOUND("Spécialité non trouvée"),

    SECRETARY_NOT_FOUND("secretaire non trouvé"),
    SECRETARY_ALREADY_EXIST("secretaire deja existe"),

    // Erreurs Patients
    PATIENT_NOT_FOUND("Patient non trouvé"),
    PATIENT_ALREADY_EXISTS("Ce patient existe déjà"),

    // Erreurs Rendez-vous
    APPOINTMENT_NOT_FOUND("Rendez-vous non trouvé"),
    APPOINTMENT_OVERLAP("Chevauchement de rendez-vous"),
    INVALID_APPOINTMENT_TIME("Horaire de rendez-vous invalide"),
    PAST_APPOINTMENT_DATE("Date de rendez-vous dans le passé"),
    APPOINTEMENT_ALREADY_CANCELED("le rendez vous est deja annuller"),
    // Erreurs de validation
    INVALID_DATA("Les données fournies sont invalides"),
    MISSING_REQUIRED_FIELD("Champ obligatoire manquant"),
    DUPLICATE_RECORD("Erreur de duplication ");
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}