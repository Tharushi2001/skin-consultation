public interface SkinConsultationManager {
  void addDoctor(Doctor doctor);
  void deleteDoctor( int medical_licence_num);
    void printList();
    void saveList();

    void loadData();
}
