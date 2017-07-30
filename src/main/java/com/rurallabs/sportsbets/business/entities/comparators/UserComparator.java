package com.rurallabs.sportsbets.business.entities.comparators;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import com.rurallabs.sportsbets.business.entities.User;

public final class UserComparator implements Comparator<User> {

	private final Locale locale;

	public UserComparator(final Locale locale) {
		super();
		this.locale = locale;
	}

	@Override
	public int compare(final User o1, final User o2) {
		final Collator collator = Collator.getInstance(this.locale);
		return collator.compare(o1.getLogin(), o2.getLogin());
	}

}
