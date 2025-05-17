async function deleteCourse(endpoint, id, redirectUrl) {
    const result = await Swal.fire({
        title: 'Are you sure?',
        text: "The chapters, lessons and resources of this course will be deleted and cannot be recovered!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Delete',
        cancelButtonText: 'Cancel'
    });

    if (result.isConfirmed) {
        const beforeUnloadHandler = (e) => {
            e.preventDefault();
            e.returnValue = '';
        };
        window.addEventListener('beforeunload', beforeUnloadHandler);

        Swal.fire({
            title: 'Deleting...',
            text: 'Please wait while the course is being deleted...',
            allowOutsideClick: false,
            allowEscapeKey: false,
            didOpen: () => {
                Swal.showLoading();
            }
        });

        try {
            const response = await fetch(`${endpoint}/${id}`, {
                method: 'DELETE'
            });

            if (response.status === 204) {
                Swal.fire('Deleted!', 'Data is deleted!', 'success').then(() => {
                    window.removeEventListener('beforeunload', beforeUnloadHandler);
                    window.location.href = redirectUrl;
                });
            } else {
                Swal.fire('Error!', 'Something went wrong.', 'error');
                window.removeEventListener('beforeunload', beforeUnloadHandler);
            }
        } catch (error) {
            console.error('Error when deleting course:', error);
            Swal.fire('Error!', 'Something went wrong!', 'error');
            window.removeEventListener('beforeunload', beforeUnloadHandler);
        }
    }
}
