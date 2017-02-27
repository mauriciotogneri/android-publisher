package com.mauriciotogneri.android.publisher;

import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Commit;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Insert;
import com.google.api.services.androidpublisher.AndroidPublisher.Edits.Listings.Update;
import com.google.api.services.androidpublisher.model.AppEdit;
import com.google.api.services.androidpublisher.model.Listing;

import java.util.Locale;

/**
 * Updates US and UK listings. Changes title, short-description, full-description and video for
 * en-US and en-GB locales.
 */
public class UpdateListing
{
    private static final String US_LISTING_TITLE = "App Title US";
    private static final String US_LISTING_SHORT_DESCRITPION = "Bacon ipsum";
    private static final String US_LISTING_FULL_DESCRIPTION = "Dessert trunk truck";

    private static final String UK_LISTING_TITLE = "App Title UK";
    private static final String UK_LISTING_SHORT_DESCRITPION = "Pancetta ipsum";
    private static final String UK_LISTING_FULL_DESCRIPTION = "Pudding boot lorry";

    private static final String LISTINGS_PROMO_VIDEO =
            "https://www.youtube.com/watch?v=ZNSLQlNSPu8";

    public static void main(String[] args)
    {
        try
        {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(ApplicationConfig.PACKAGE_NAME),
                    "ApplicationConfig.PACKAGE_NAME cannot be null or empty!");

            // Create the API service.
            AndroidPublisher service = AndroidPublisherHelper.init(
                    ApplicationConfig.APPLICATION_NAME, ApplicationConfig.SERVICE_ACCOUNT_EMAIL);
            final Edits edits = service.edits();

            // Create an edit to update listing for application.
            Insert editRequest = edits
                    .insert(ApplicationConfig.PACKAGE_NAME,
                            null /** no content */);
            AppEdit edit = editRequest.execute();
            final String editId = edit.getId();
            System.out.println(String.format("Created edit with id: %s", editId));

            // Update listing for US version of the application.
            final Listing newUsListing = new Listing();
            newUsListing.setTitle(US_LISTING_TITLE)
                    .setFullDescription(US_LISTING_FULL_DESCRIPTION)
                    .setShortDescription(US_LISTING_SHORT_DESCRITPION)
                    .setVideo(LISTINGS_PROMO_VIDEO);

            Update updateUSListingsRequest = edits
                    .listings()
                    .update(ApplicationConfig.PACKAGE_NAME,
                            editId,
                            Locale.US.toString(),
                            newUsListing);
            Listing updatedUsListing = updateUSListingsRequest.execute();
            System.out.println(String.format("Created new US app listing with title: %s",
                    updatedUsListing.getTitle()));

            // Create and update listing for UK version of the application.
            final Listing newUkListing = new Listing();
            newUkListing.setTitle(UK_LISTING_TITLE)
                    .setFullDescription(UK_LISTING_FULL_DESCRIPTION)
                    .setShortDescription(UK_LISTING_SHORT_DESCRITPION)
                    .setVideo(LISTINGS_PROMO_VIDEO);

            Update updateUkListingsRequest = edits
                    .listings()
                    .update(ApplicationConfig.PACKAGE_NAME,
                            editId,
                            Locale.UK.toString(),
                            newUkListing);
            Listing updatedUkListing = updateUkListingsRequest.execute();
            System.out.println(String.format("Created new UK app listing with title: %s",
                    updatedUkListing.getTitle()));

            // Commit changes for edit.
            Commit commitRequest = edits.commit(ApplicationConfig.PACKAGE_NAME, editId);
            AppEdit appEdit = commitRequest.execute();
            System.out.println(String.format("App edit with id %s has been comitted", appEdit.getId()));

        }
        catch (Exception e)
        {
            System.err.println("Exception was thrown while updating listing: " + e.getMessage());
        }
    }
}