package org.example.UI;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.example.DAO.DoctorDAO;
import org.example.DAO.PatientDAO;
import org.example.DAO.RecipePriorityDAO;
import org.example.DAO.SpecializationDAO;
import org.example.DTO.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The type Edit add modal ui.
 *
 * @param <E> the type parameter
 */
public class EditAddModalUI<E> extends Dialog {

    private VerticalLayout layout = new VerticalLayout();

    private Button okButton;

    private Button cancelButton;

    private TextField nameField;

    private TextField patronymicField;

    private TextField surnameField;

    private TextField phoneNumberField;

    private ComboBox specializationBox;

    private TextArea descriptionField;

    private ComboBox patientBox;

    private ComboBox doctorBox;

    private DatePicker creationDateField;

    private TextField validityField;

    private TextArea coursesField;

    private NumberField policyField;

    private TextField registrationField;

    private ComboBox priorityBox;

    private static final int ID_ONE = 1;

    private static final int ID_THREE = 3;

    private static final int ID_FOUR = 4;

    private String role = null;

    public EditAddModalUI(final AbstractUI abstractUI, String role) {
        if (abstractUI instanceof DoctorUI) {
            createDoctorLayout();
            add(layout);
            createButtons();
            add(okButton, cancelButton);
            okButton.addClickListener(e -> {
                Doctor doctor =
                        null;
                doctor = new Doctor(abstractUI.getEntity().size() + 1,
                        nameField.getValue(),
                        patronymicField.getValue(),
                        surnameField.getValue(),
                        specializationBox.getValue().toString(),
                        coursesField.getValue());
                try {
                    abstractUI.getDao().add(doctor);
                    abstractUI.refreshGrid();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                close();
            });

        } else if (abstractUI instanceof PatientUI) {
            createPatientLayout();
            add(layout);
            createButtons();
            add(okButton, cancelButton);
            okButton.addClickListener(e -> {
                Patient patient = new Patient(abstractUI.getEntity().size() + 1,
                        nameField.getValue(),
                        patronymicField.getValue(),
                        surnameField.getValue(),
                        phoneNumberField.getValue(), policyField.getValue().longValue(), registrationField.getValue());
                try {
                    abstractUI.getDao().add(patient);
                    abstractUI.refreshGrid();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                close(); //TODO
            });

        } else if (abstractUI instanceof RecipeUI) {
                createRecipeLayout();
                add(layout);
                createButtons();
                add(okButton, cancelButton);
                 okButton.addClickListener(e -> {
                   Recipe recipe = new Recipe(abstractUI.getEntity().size() + 1,
                           descriptionField.getValue(),
                           (Patient) patientBox.getValue(),
                           (Doctor) doctorBox.getValue(),
                           Date.valueOf(creationDateField.getValue()),
                           Integer.parseInt(validityField.getValue()),
                           (RecipePriority) priorityBox.getValue());
                    try {
                        abstractUI.getDao().add(recipe);
                        abstractUI.refreshGrid();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    close();
                });
        }
        //setModal(true);
        //setContent(layout);
        cancelButton.addClickListener(e -> {
            close();
        });
    }


    public EditAddModalUI(final AbstractUI abstractUI, final E object, String role) {

            if (abstractUI instanceof DoctorUI) {
                Doctor doctor = (Doctor) object;
                createDoctorLayout(doctor.getName(), doctor.getPatronymic(),
                        doctor.getSurname(), doctor.getSpecialization(), doctor.getCourses());
                createButtons();
                add(layout);
                add(okButton, cancelButton);
                okButton.addClickListener(e -> {
                    try {
                        Doctor newDoctor = new Doctor(abstractUI.getEntity().indexOf(abstractUI.getSelectedItem()),
                                nameField.getValue(),
                                patronymicField.getValue(),
                                surnameField.getValue(),
                                specializationBox.getValue().toString(),
                                coursesField.getValue());
                        abstractUI.getDao().update(newDoctor, abstractUI.getSelectedItem().getId());
                        abstractUI.refreshGrid();
                        close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
            } else if (abstractUI instanceof PatientUI) {
                Patient patient = (Patient) object;
                createPatientLayout(patient.getName(), patient.getPatronymic(),
                        patient.getSurname(), patient.getPhoneNumber(), patient.getPolicy(), patient.getRegistration());
                createButtons();
                add(layout);
                add(okButton, cancelButton);
                okButton.addClickListener(e -> {
                    try {
                        Patient newPatient = new Patient(abstractUI.getEntity().indexOf(abstractUI.getSelectedItem()),
                                nameField.getValue(),
                                patronymicField.getValue(),
                                surnameField.getValue(),
                                phoneNumberField.getValue(), patient.getPolicy(), patient.getRegistration());
                        abstractUI.getDao().update(newPatient, abstractUI.getSelectedItem().getId());
                        abstractUI.refreshGrid();
                        close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } //TODO
                });
            } else if (abstractUI instanceof RecipeUI) {
                Recipe recipe = (Recipe) object;
                createRecipeLayout(recipe.getDescription(), recipe.getPatient(),
                        recipe.getDoctor(), recipe.getCreationDate(), recipe.getValidity(),
                        recipe.getPriority());
                createButtons();
                add(layout);
                add(okButton, cancelButton);
                okButton.addClickListener(e -> {
                    try {
                        Recipe newRecipe = new Recipe(abstractUI.getEntity().indexOf(abstractUI.getSelectedItem()),
                                descriptionField.getValue(),
                                (Patient) patientBox.getValue(),
                                (Doctor) doctorBox.getValue(),
                                Date.valueOf(creationDateField.getValue()),
                                Integer.parseInt(validityField.getValue()),
                                (RecipePriority) priorityBox.getValue());
                        abstractUI.getDao().update(newRecipe, abstractUI.getSelectedItem().getId());
                        abstractUI.refreshGrid();
                        close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
            }
            //setModal(true);
            //setContent(layout);
            cancelButton.addClickListener(e -> {
                close();
            });
        }

    /**
     * Create person layout.
     */
    public void createPersonLayout() {
        nameField = new TextField("Имя");
        patronymicField = new TextField("Отчество");
        surnameField = new TextField("Фамилия");
        layout.add(nameField, patronymicField, surnameField);
    }

    /**
     * Create patient layout.
     */
    public void createPatientLayout() {
        createPersonLayout();
        phoneNumberField = new TextField("Номер телефона");
        //options.prefix("+7", "");
        phoneNumberField.setMaxLength(11);
        policyField = new NumberField("Номер полиса");
        registrationField = new TextField("Адрес прописки");
        layout.add(phoneNumberField, policyField, registrationField);
    }

    /**
     * Create patient layout.
     *
     * @param name        the name
     * @param patronymic  the patronymic
     * @param surname     the surname
     * @param phoneNumber the phone number
     */
    public void createPatientLayout(final String name, final String patronymic, final String surname,
                                    final String phoneNumber, final long policy, final String registration) {
        createPersonLayout();
        nameField.setValue(name);
        patronymicField.setValue(patronymic);
        surnameField.setValue(surname);
        phoneNumberField = new TextField("Номер телефона");
        phoneNumberField.setValue(phoneNumber);
        phoneNumberField.setMaxLength(11);
        policyField = new NumberField("Номер полиса");
        policyField.setValue(Double.parseDouble(String.valueOf(policy)));
        registrationField = new TextField("Адрес прописки");
        registrationField.setValue(registration);
        layout.add(phoneNumberField, policyField, registrationField);
    }

    /**
     * Create doctor layout.
     *
     * @param name           the name
     * @param patronymic     the patronymic
     * @param surname        the surname
     * @param specialization the specialization
     */
    public void createDoctorLayout(final String name, final String patronymic, final String surname,
                                   final String specialization, final String courses) {
        createPersonLayout();
        final String[] buffer = new String[1];
        /*Upload.Receiver receiver = new Upload.Receiver() {
            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
                try {
                    return new FileOutputStream(new File(filename));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        upload = new Upload("Upload", receiver);
        upload.addSucceededListener(event -> {

        });
        StreamResource resource = new StreamResource(() -> {
            try {
                System.out.println(buffer[0]);
                return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(buffer[0])));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }, "dummyImageName.jpg");
        image = new Image("PIC", resource);
        horizontalLayout.addComponent(image);*/
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        //horizontalLayout.add(upload);
        layout.add(horizontalLayout);
        nameField.setValue(name);
        patronymicField.setValue(patronymic);
        surnameField.setValue(surname);
        specializationBox = new ComboBox<String>("Специализация", new SpecializationDAO().getAll());
        specializationBox.setItemLabelGenerator((ItemLabelGenerator<String>) Object::toString);
        //ListDataProvider dataProvider = DataProvider.ofCollection(new SpecializationDAO().getAll());
        //specializationBox.setItemLabelGenerator((ItemLabelGenerator<String>) Object::toString);
        //specializationBox.setDataProvider(dataProvider);
        specializationBox.setValue(specialization);
        layout.add(specializationBox);
        coursesField = new TextArea("Courses");
        if (courses != null)
        coursesField.setValue(courses);
        layout.add(coursesField);
    }

    /**
     * Create doctor layout.
     */
    public void createDoctorLayout() {
        /*final String[] buffer = new String[1];
        Upload.Receiver receiver = new Upload.Receiver() {
            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
                try {
                    buffer[0] = filename;
                    return new FileOutputStream(filename);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        upload = new Upload("Upload", receiver);
        upload.addSucceededListener(event -> {

        });
        StreamResource resource = new StreamResource(() -> {
            try {
                System.out.println(buffer[0]);
                return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(buffer[0])));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }, "dummyImageName.jpg");*/
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        /*image = new Image("PIC", resource);
        horizontalLayout.addComponent(image);*/
        layout.add(horizontalLayout);
        createPersonLayout();
        specializationBox = new ComboBox<String>("Специализация", new SpecializationDAO().getAll());
        //ListDataProvider dataProvider = DataProvider.ofCollection(new SpecializationDAO().getAll());
        specializationBox.setItemLabelGenerator((ItemLabelGenerator<String>) Object::toString);

        //specializationBox.setDataProvider(dataProvider);
        layout.add(specializationBox);
        coursesField = new TextArea("Courses");
        layout.add(coursesField);
    }

    /**
     * Sets patient box.
     */
    public void setPatientBox() {
        patientBox = new ComboBox("Пациент", new PatientDAO().getAll());
        patientBox.setItemLabelGenerator((ItemLabelGenerator<Patient>) Person::getFullName);
        patientBox.setWidth("300px");
        patientBox.setRequired(true);
    }

    /**
     * Sets doctor box.
     */
    public void setDoctorBox() {
        doctorBox = new ComboBox("Доктор", new DoctorDAO().getAll());
        doctorBox.setItemLabelGenerator((ItemLabelGenerator<Doctor>) Doctor::getFullName);
        doctorBox.setWidth("300px");
        doctorBox.setRequired(true);
    }

    /**
     * Sets priority box.
     */
    public void setPriorityBox() {
        priorityBox = new ComboBox("Приоритет", new RecipePriorityDAO().getAll());
        priorityBox.setItemLabelGenerator((ItemLabelGenerator<RecipePriority>) RecipePriority::getPriority);
        priorityBox.setRequired(true);
    }

    /**
     * Create recipe layout.
     */
    public void createRecipeLayout() {
        descriptionField = new TextArea("Описания");
        descriptionField.setWidth("300px");
        setPatientBox();
        setDoctorBox();
        creationDateField = new DatePicker("Дата создания");
        creationDateField.setValue(LocalDate.now());
        creationDateField.setMin(LocalDate.now());
        validityField = new TextField("Срок действия (в днях)");
        setPriorityBox();
        layout.add(descriptionField, patientBox, doctorBox,
                creationDateField, validityField, priorityBox);
    }

    /**
     * Create recipe layout.
     *
     * @param description  the description
     * @param patient      the patient
     * @param doctor       the doctor
     * @param creationDate the creation date
     * @param validity     the validity
     * @param priority     the priority
     */
    public void createRecipeLayout(final String description, final Patient patient, final Doctor doctor,
                                   final Date creationDate,
                                   final int validity, final RecipePriority priority) {
        createRecipeLayout();
        descriptionField.setValue(description);
        patientBox.setValue(patient);
        doctorBox.setValue(doctor);
        //creationDateField.setRangeStart(null);
        creationDateField.setValue(creationDate.toLocalDate());
        validityField.setValue(String.valueOf(validity));
        priorityBox.setValue(priority);
    }

    /**
     * Create buttons.
     */
    public void createButtons() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        okButton = new Button("Ok");
        cancelButton = new Button("Отмена");
        horizontalLayout.add(okButton, cancelButton);
        layout.add(horizontalLayout);
    }
}
