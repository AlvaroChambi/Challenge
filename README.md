# Challenge

To implement this application the recommended google architecure is bein used, ViewModel, LiveData, and Repositories are being 
implemented when needed.

To handle the navigation Activities are being used as a containers of fragments, each screen is conformed of an Activity with a
Fragment attached.

To share data between activities, in this case the ProductsActivity and the CheckoutActivity, Parcelables are being used which
along ViewModels will handle all data persistance that is needed for the CheckoutActivity.
