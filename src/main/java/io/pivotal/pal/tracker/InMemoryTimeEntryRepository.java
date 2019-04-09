package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    private long currentId = 1L;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
       timeEntries.put(id,newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        TimeEntry findTimeEntry = timeEntries.get(id);
        return findTimeEntry;
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        list.addAll(timeEntries.values());
        return list;
    }


    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (find(id) == null) return null;
        TimeEntry updatedTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntries.replace(id, updatedTimeEntry);
        return updatedTimeEntry;
    }

    @Override
    public void delete(Long id) {
         timeEntries.remove(id);
    }
}
