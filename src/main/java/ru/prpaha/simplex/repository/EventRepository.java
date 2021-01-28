package ru.prpaha.simplex.repository;

import com.google.gson.Gson;
import okhttp3.Call;
import ru.prpaha.simplex.api.DefaultApi;
import ru.prpaha.simplex.invoker.ApiException;
import ru.prpaha.simplex.model.Event;
import ru.prpaha.simplex.model.Events;

/**
 * @author Proskurin Pavel (prpaha@rambler.ru)
 */
public class EventRepository extends AbstractApiRepository {

    private final DefaultApi defaultApi;

    public EventRepository(DefaultApi defaultApi, Gson gson) {
        super(gson);
        this.defaultApi = defaultApi;
    }

    public Events getEvents() throws ApiException {
        Call getEventsCall = defaultApi.getEventsCall(null);
        String respBody = call(getEventsCall);
        return parseObject(respBody, Events.class);
    }

    public void deleteEvent(final Event event) throws ApiException {
        if (event == null) {
            return;
        }
        Call getEventsCall = defaultApi.deleteEventCall(event.getEventId(), null);
        call(getEventsCall);
    }
}
